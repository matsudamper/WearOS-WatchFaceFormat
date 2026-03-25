package net.matsudamper.watchface

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.RadioButton
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.ToggleChip
import androidx.wear.compose.material.ToggleChipDefaults
import androidx.wear.watchface.editor.EditorSession
import androidx.wear.watchface.style.UserStyle
import androidx.wear.watchface.style.UserStyleSetting
import androidx.wear.watchface.style.UserStyleSetting.ListUserStyleSetting
import kotlinx.coroutines.launch

class WatchFaceConfigActivity : ComponentActivity() {

    private val editorSessionState = mutableStateOf<EditorSession?>(null)
    private val errorState = mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            runCatching {
                editorSessionState.value = EditorSession.createOnWatchEditorSession(
                    this@WatchFaceConfigActivity
                )
            }.onFailure { e ->
                errorState.value = e.message ?: e.javaClass.simpleName
            }
        }

        setContent {
            MaterialTheme {
                val editorSession by editorSessionState
                val error by errorState
                WatchFaceConfigScreen(
                    editorSession = editorSession,
                    error = error,
                    onClose = { finish() },
                )
            }
        }
    }

    override fun onDestroy() {
        editorSessionState.value?.close()
        super.onDestroy()
    }
}

// ─── Color lookup (mirrors build-logic Colors.kt, needed at runtime) ──────────

private val OPTION_COLOR_MAP: Map<String, Long> = mapOf(
    "Red" to 0xFFE57373L,
    "Pink" to 0xFFF06292L,
    "Purple" to 0xFFBA68C8L,
    "DeepPurple" to 0xFF7E57C2L,
    "Indigo" to 0xFF7986CBL,
    "Blue" to 0xFF64B5F6L,
    "LightBlue" to 0xFF4FC3F7L,
    "Cyan" to 0xFF4DD0E1L,
    "Teal" to 0xFF4DB6ACL,
    "Green" to 0xFF81C784L,
    "LightGreen" to 0xFFAED581L,
    "Lime" to 0xFFDCE775L,
    "Yellow" to 0xFFFFF176L,
    "Amber" to 0xFFFFD54FL,
    "Orange" to 0xFFFFB74DL,
    "DeepOrange" to 0xFFFF8A65L,
    "Brown" to 0xFFA1887FL,
    "Grey" to 0xFFE0E0E0L,
    "BlueGrey" to 0xFF90A4AEL,
    "White" to 0xFFFFFFFFL,
)

// ─── Helpers ──────────────────────────────────────────────────────────────────

/**
 * Tries to resolve [name] as a string resource key; falls back to [name] itself.
 * This handles WFF displayName values that are stored as raw resource keys
 * (e.g. "color_name_analog_hand") rather than @string references.
 */
private fun Context.resolveDisplayName(name: CharSequence): String {
    val key = name.toString()
    val resId = resources.getIdentifier(key, "string", packageName)
    return if (resId != 0) getString(resId) else key
}

// ─── Screen routing ───────────────────────────────────────────────────────────

@Composable
private fun WatchFaceConfigScreen(
    editorSession: EditorSession?,
    error: String?,
    onClose: () -> Unit,
) {
    when {
        error != null -> {
            ErrorScreen(message = error, onClose = onClose)
        }
        editorSession == null -> {
            LoadingScreen()
        }
        else -> {
            val userStyle by editorSession.userStyle.collectAsStateWithLifecycle()
            val settings = remember(editorSession) {
                editorSession.userStyleSchema.userStyleSettings
                    .filterIsInstance<ListUserStyleSetting>()
            }

            var selectedSetting by remember { mutableStateOf<ListUserStyleSetting?>(null) }

            val current = selectedSetting
            if (current != null) {
                OptionPickerScreen(
                    setting = current,
                    currentStyle = userStyle,
                    onOptionSelected = { option ->
                        val mutable = userStyle.toMutableUserStyle()
                        mutable[current] = option
                        editorSession.userStyle.value = mutable.toUserStyle()
                        selectedSetting = null
                    },
                    onBack = { selectedSetting = null },
                )
            } else {
                SettingsListScreen(
                    settings = settings,
                    currentStyle = userStyle,
                    onSettingSelected = { selectedSetting = it },
                    onClose = onClose,
                )
            }
        }
    }
}

// ─── Loading ──────────────────────────────────────────────────────────────────

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

// ─── Error ────────────────────────────────────────────────────────────────────

@Composable
private fun ErrorScreen(message: String, onClose: () -> Unit) {
    val listState = rememberScalingLazyListState()
    Scaffold(positionIndicator = { PositionIndicator(scalingLazyListState = listState) }) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 40.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Text(
                    text = stringResource(R.string.config_error_title),
                    style = MaterialTheme.typography.title3,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.error,
                )
            }
            item {
                Text(
                    text = message,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                )
            }
            item {
                Chip(
                    label = { Text(stringResource(R.string.config_close)) },
                    onClick = onClose,
                    colors = ChipDefaults.primaryChipColors(),
                )
            }
        }
    }
}

// ─── Settings list ────────────────────────────────────────────────────────────

@Composable
private fun SettingsListScreen(
    settings: List<ListUserStyleSetting>,
    currentStyle: UserStyle,
    onSettingSelected: (ListUserStyleSetting) -> Unit,
    onClose: () -> Unit,
) {
    val context = LocalContext.current
    val listState = rememberScalingLazyListState()

    Scaffold(positionIndicator = { PositionIndicator(scalingLazyListState = listState) }) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 40.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Text(
                    text = stringResource(R.string.config_title),
                    style = MaterialTheme.typography.title3,
                    textAlign = TextAlign.Center,
                )
            }

            items(settings) { setting ->
                val selectedOption =
                    currentStyle[setting] as? ListUserStyleSetting.ListOption
                val settingLabel = context.resolveDisplayName(setting.displayName)
                val optionLabel = selectedOption?.displayName
                    ?.let { context.resolveDisplayName(it) }
                    ?: ""
                val optionColor = selectedOption?.id?.value
                    ?.let { OPTION_COLOR_MAP[it] }
                    ?.let { Color(it) }

                Chip(
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(
                            text = settingLabel,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                    secondaryLabel = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (optionColor != null) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(CircleShape)
                                        .background(optionColor),
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                            }
                            Text(
                                text = optionLabel,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    },
                    onClick = { onSettingSelected(setting) },
                    colors = ChipDefaults.secondaryChipColors(),
                )
            }

            item {
                Chip(
                    label = { Text(stringResource(R.string.config_close)) },
                    onClick = onClose,
                    colors = ChipDefaults.primaryChipColors(),
                )
            }
        }
    }
}

// ─── Option picker ────────────────────────────────────────────────────────────

@Composable
private fun OptionPickerScreen(
    setting: ListUserStyleSetting,
    currentStyle: UserStyle,
    onOptionSelected: (ListUserStyleSetting.ListOption) -> Unit,
    onBack: () -> Unit,
) {
    BackHandler(onBack = onBack)

    val context = LocalContext.current
    val listState = rememberScalingLazyListState()
    val currentOption = currentStyle[setting] as? ListUserStyleSetting.ListOption

    Scaffold(positionIndicator = { PositionIndicator(scalingLazyListState = listState) }) {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 40.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Text(
                    text = context.resolveDisplayName(setting.displayName),
                    style = MaterialTheme.typography.title3,
                    textAlign = TextAlign.Center,
                )
            }

            items(setting.options) { option ->
                val isSelected = option.id == currentOption?.id
                val optionLabel = context.resolveDisplayName(option.displayName)
                val optionColor = OPTION_COLOR_MAP[option.id.value]?.let { Color(it) }

                ToggleChip(
                    modifier = Modifier.fillMaxWidth(),
                    checked = isSelected,
                    onCheckedChange = { checked -> if (checked) onOptionSelected(option) },
                    label = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (optionColor != null) {
                                Box(
                                    modifier = Modifier
                                        .size(14.dp)
                                        .clip(CircleShape)
                                        .background(optionColor),
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                            }
                            Text(
                                text = optionLabel,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    },
                    toggleControl = {
                        RadioButton(selected = isSelected)
                    },
                    colors = ToggleChipDefaults.toggleChipColors(),
                )
            }
        }
    }
}
