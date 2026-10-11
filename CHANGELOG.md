# SoulSwap Changes

## 0.3.0-test — SoulForge (2026-10-10)
- Promoted all six new Dragon Ball expansion cards into the native source, bringing the offline built-in gacha pool from 3 to 9 distinct variant IDs.
- Locked all nine cards to `soul-shards-v1`, while allowing future card imports to inherit progression.
- Added Shard Workshop as a sixth bottom navigation destination.
- Added Awakening +1..+5 (shard costs: 2 / 4 / 12 / 20 / 30), Soul Aura at +5, combat bonuses, and dynamic displayed power.
- Added per-technique rank upgrades +1/+2 (costs 3/7), with damage or guard improvement and blocked-move checks.
- Added shard fusion (4 duplicate shards → 1 universal Soul Fragment); at most half of an Awakening cost may be universal.
- Persisted awakening, skill ranks, universal fragments and cosmetics in saveVersion 3 and added migration from v0.2 saves.
- New card update schema v2 includes progression; v1 and raw legacy arrays remain importable.
- Reused existing dynamic rarity frames and five-field front overlay.
- Added GAMEBUILD handoff and future update contract.

## 0.2.0-test
- New game starts empty; starter free 10-pull with guaranteed SR+.
- Added Settings restart, optional imported-pack wipe; retained existing saves.
- Randomized 1v1 matchmaking (95% ±2 levels, 5% +3..+10).

## 0.1.0-test
- First three authored DB Goku-mind swaps, dynamic card frames, battles, JSON card importer, and duplicate shards.
