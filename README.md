# SoulSwap v0.3 — SoulForge Test

**Nine-card offline Android gacha game** with 1v1 fights and a single female-vessel/male-mind combined collectible card per character. Built using Java Android WebView + HTML/CSS/JS.

### Play

Fresh games own zero cards. Visit Summon for the free 10-pull (one guaranteed Epic/SR or better). View card details in Collection, fight in Battle, and spend shards in the **Shards** workshop. Import additional card JSON through Updates. Settings → Restart begins a fresh run (with optional deletion of previously imported packs).

### SoulForge rules

Duplicate card → +2 character shards. Awakening ranks +1..+5 cost 2,4,12,20,30 respectively; unlock bonuses in HP/stats, passive, techniques, strain, ultimate and cosmetic aura. Technique levels +1 and +2 cost 3 and 7 shards. Fuse 4 character shards for one Soul Fragment; universal fragments can substitute for up to half of an awakening cost. All nine built-in cards and all future imported cards use these rules.

### Development

`app/src/main/assets/index.html` — main UI and battle engine.
`app/src/main/assets/seed_cards.json` — nine authored cards (complete mechanics).
`app/src/main/assets/cards/` — nine canonical images.
`app/src/main/assets/frames/` — five locked rarity-overlay PNGs.
`CARD_UPDATE_SCHEMA_v2.json` — template for future card updates.
`GAMEBUILD_HANDOFF.md` — complete project constraints for a future chat.
`CHANGELOG.md` — development history.

Build: `gradle --no-daemon :app:assembleDebug` using Java 17 and Android SDK 35, or run the GitHub workflow. App ID `com.soulswap.game`, version `0.3.0-test`.

Save storage is WebView localStorage key `soulswap-save-v1` plus imported JSON cards in IndexedDB. This APK is an unofficial fan development prototype.

**For future chats always provide the new versioned GAMEBUILD ZIP along with the APK**. The GameBuild archive contains the *entire* source, assets and handoff notes; an APK alone is not sufficient to resume development reliably.
