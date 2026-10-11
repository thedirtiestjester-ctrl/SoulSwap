# SoulSwap v0.3.2 — Summon Currency Hotfix

Cause: checkEnd() awards summon tokens and saves the new amount, but v0.3 only refreshed summon button `.disabled` flags within syncAll(), not after battle payouts. This left the ×1 button stuck disabled after earning 100+ tokens.

Changes: call refreshSummon() from saveState() and on entering the summon screen; show live affordability. A fresh free 10-pull still requires at least one SR-or-higher result; duplicates remain shards. Zero starting cards and JSON update packs are unchanged.

Regression checks: (1) free ×10 then 0 tokens: both paid buttons disabled; (2) +55 then +55 battle earnings: ×1 enabled at 110; (3) spend 100: ×1 disabled below 100; (4) at 900: both enabled; (5) ×10 consumes 900; (6) reopen tab: buttons reflect latest wallet. Source tests are in GAMEBUILD v0.3.2 ZIP.

Build: GitHub Actions > Build SoulSwap Android APK > latest main push. Imported card packs live on device, not in the source repo. Debug builds may not share signing keys; see backup warning.
