# SoulSwap GAMEBUILD — Persistent Project Handoff

**Canonical baseline:** SoulSwap `v0.3.0-test` / SoulForge Update (2026-10-10)  
**GitHub repo:** https://github.com/thedirtiestjester-ctrl/SoulSwap  
**Android application ID:** `com.soulswap.game`  
**Game:** offline, WebView-based Android 1v1 body-swap gacha battler.

## How to continue in a new ChatGPT chat

1. Upload **`SoulSwap_GAMEBUILD_v0.3.zip`**, not merely an APK. The ZIP contains the complete Android source, all 9 character images, rarity overlays, the seed database, tests, exact feature rules, and the card update template.
2. Tell ChatGPT: **"Continue SoulSwap development from this GAMEBUILD baseline. Preserve all existing cards, shard progression, save migrations and update importer. Build the next version and return a new APK plus a new versioned GAMEBUILD ZIP."**
3. Also upload any *new* generated artwork or JSON card exports you want added after this baseline.
4. Each future update must return both (a) an installable debug APK or verified GitHub build link, and (b) a new `SoulSwap_GAMEBUILD_vX.Y.zip` containing all code/assets/data/tests/history. Never assume previous chat file paths are accessible in a new chat.

## Locked decisions and gameplay rules

- Every card represents **one adult male consciousness in one female body**; no separate mind/body collectible cards.
- The **mind determines techniques attempted, tactics, memory, mastery**. The **body determines physical stats, limits and what/how an attempted ability manifests**. New body-specific skills can be discovered through leveling.
- **Front of every card:** rarity, `Female Body (Male Mind)` name, variant title, short flavor text, and dynamic Power rating; no other stats or labels on the card front. Character illustration occupies almost all the area. Rarity frame PNGs are transparent and applied by the app, not baked into the artwork.
- **Back of every card:** detailed stats, mind identity, vessel identity, original techniques, discovered techniques, passive, ultimate, restrictions, lore, awakening and shard information.
- A fresh save begins with **zero owned cards and no currency**. A one-time **free 10-pull** guarantees at least **one SR/Epic-or-higher**. Duplicates grant **2 character-specific shards**, not duplicate fighters.
- Battle opponents are randomly selected from the **full installed card pool** (not only owned cards). **95%** of battles: opponent level player level ±2; **5%**: opponent level player level +3 to +10 (bounded by level cap 25). Rare encounter is labeled ELITE.
- Settings includes **Restart Game**, confirmed by typing `RESTART`. Restart clears owned cards, progression, shards, currency and free-starter flag; keep installed JSON card packs unless the optional removal checkbox is checked.
- **SoulForge**: awakening +1..+5 with shard costs **2,4,12,20,30**; skill rank upgrades +1/+2 cost **3,7**; shards fuse **4:1** into universal Soul Fragments; universal fragments substitute for **at most 50%** (floor) of an awakening cost; they cannot level techniques. Rank 5 unlocks an aura visual.
- All new cards imported from JSON receive SoulForge defaults automatically. Preserve backward compatibility with schema 1.0 and raw legacy card arrays; new canonical card update schema is 2.0.
- Fan prototype using licensed franchise identities; not authorized for commercial distribution without rights/licensing.

## All 9 canonical card IDs and built-in art

| Card ID | Front | Rarity |
| --- | --- | --- |
| `ss-db-goku-18` | Android 18 (Goku): Limitless Engine | Legendary |
| `ss-db-goku-videl` | Videl (Goku): Saiyan Spirit, Human Limits | Rare |
| `ss-db-goku-bulma` | Bulma (Goku): Genius Body, Fighter Mind | Common |
| `ss-db-goku-videl-mountain` | Videl (Goku): Mountain Training Misfit | Rare |
| `ss-db-goku-bulma-lab` | Bulma (Goku): Laboratory Left Hook | Common |
| `ss-db-goku-caulifla-mountain` | Caulifla (Goku): Borrowed Saiyan Spark | Legendary |
| `ss-db-vegeta-18-gravity` | Android 18 (Vegeta): Royal Machine | Legendary |
| `ss-db-vegeta-chichi-arena` | Chi-Chi (Vegeta): Tournament Tyrant | Rare |
| `ss-db-vegeta-bulma-badman` | Bulma (Vegeta): Badman in the Wrong Body | Common |

All nine fully authored combat cards, including exact values and abilities, are in `app/src/main/assets/seed_cards.json` (human-readable) and `seed_cards.js` (runtime). Art is in `app/src/main/assets/cards/*.jpg`. Overlay art is in `app/src/main/assets/frames/*.png`.

## Implementation details

- Source: Android Gradle app under `app`, Java `MainActivity` loads `file:///android_asset/index.html` into a WebView. The game's HTML/CSS/JS in `app/src/main/assets/index.html` is the core engine.
- Save key: `localStorage['soulswap-save-v1']` intentionally retained across versions; v0.3 save version **3** includes new `awakening`, `skillRanks`, `universalFragments`, `cosmetics` fields. Player card art packs persist separately in IndexedDB `soulswap-packs` / `data['customCards']`.
- New saves must NOT auto-grant all seeded cards. Older v0.1 saves migrate to full owned collection (legacy preservation). Existing v0.2 saves retain owned cards and duplicate shards; new SoulForge fields initialize empty/zero.
- JSON import: `type:'soulswap-card-update'`, `schemaVersion:'2.0'`, `cards:[...]` with unique `id` and `imageBase64`; previous 1.0 and raw legacy arrays also accepted. Imported cards join gacha pool but not ownership. If same male/female pair and no explicit ID, derive a variant ID from image prompt/fileName to avoid clobbering the base card. Do not change IDs of established cards because progress is ID-keyed.
- Card shard configuration stored as `progression` per card; default model `soul-shards-v1`. Duplicate payout, cap and costs are consistent for every card. To create future authored cards, use `CARD_UPDATE_SCHEMA_v2.json` as the starting example.
- Gameplay actual stats calculated in `fighter`, `effectiveStat`, `attack`, `passiveTurn`; normal CPU enemies do not have player-awakening buffs.
- GitHub Actions `.github/workflows/android.yml` builds Gradle debug APK, artifact `SoulSwap-0.3-debug-apk`. CompileSdk 35, targetSdk 28, Java 17.

## SoulForge bonuses

- Every awakening increases HP and effective stats by +3% per rank. Passive numeric effectiveness increases 10% at +2. Damage techniques get +7% at +3. Strained-recoil drops from 8% to 4% at +4. Ultimate damage gets +15% at +5. Power displayed on the card front increases with each rank and technique upgrade.
- Every technique evolves at most twice. Attack power gets +8% per skill rank. Guard techniques restore +4 extra energy per skill rank. Blocked and not-yet-unlocked techniques cannot evolve.
- +5 grants CSS `soul-aura` visual on the card front. The amount a player can pay with universal fragments toward awakening is capped at `floor(awakeningCost / 2)`.

## Required future deliverables

- Updated **installable APK**, compile/run/test to the extent available (do not claim compiled if not actually compiled).
- Updated **full versioned GAMEBUILD ZIP** with the complete project + all newly added artwork/data/tests and this handoff updated with the new decisions. Re-upload the ZIP each time: this is what lets a brand-new chat resume development.
- List changes and test results, explain whether Android APK signing permits install-over; preserve player saves where possible.
