#!/usr/bin/env bash
set -euo pipefail
shopt -s nullglob

for file in "$CLAUDE_PROJECT_DIR"/docs/agent-*.md; do
  printf '\n--- %s ---\n' "${file#"$CLAUDE_PROJECT_DIR"/}"
  cat "$file"
  printf '\n'
done
