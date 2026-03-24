#!/usr/bin/env bash
set -euo pipefail
mkdir -p deliverables
python - <<'PY'
import zipfile, pathlib
out=pathlib.Path('deliverables/coal-config-deliverables.zip')
paths=['docs/requirements-spec.md','docs/startup-guide.md','backend/module-design.md','frontend/module-design.md','sql/coal_config_schema.sql','scripts/package.sh']
with zipfile.ZipFile(out,'w',compression=zipfile.ZIP_DEFLATED) as z:
    for p in paths:
        z.write(p,p)
print('packaged', out)
PY
