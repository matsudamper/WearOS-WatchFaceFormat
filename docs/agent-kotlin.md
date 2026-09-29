# Kotlin 詳細スタイル

## 基本
- Kotlin 公式コーディング規約に従う
- 基本は `.editorconfig`
- デフォルト引数はなるべく使わない（既存は可。Compose の `Modifier` だけは倣わない）
- `@Suppress` / lint ignore の勝手追加禁止
- import はワイルドカード禁止。FQCN より import して短縮名

## スコープ関数
- 戻り値を使わないのに戻り値ありスコープ関数を使わない（`run` NG / 副作用なら `apply` 等）
- 長い処理と `?.let { ... } ?: x` を避け、if で分岐する
- 副作用だけの `?.also` より if

## null / コレクション
- `!!` 禁止（必要なら null チェック後に `val` へ載せ替え）
- 不要な `?: ""` は `orEmpty()`。List も同様
- `emptyList()` / `emptyMap()` より `listOf()` / `mapOf()`（Set/Mapも同様）
- `indexOf` は `takeIf { it >= 0 }` で -1 を意識しにくくする

## Mutable
- `var` はなるべく使わず `val`。どうしても必要なら関数切り出しを検討
- Compose の `mutableStateOf` は例外だが、不要なら使わない
- MutableList / MutableMap はなるべく避け、`buildList` 等を検討

## 定義の順番
- 変数 / 関数の順
- public / internal / private の順
