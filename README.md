# SoulSwap — Android Test v0.1

An **offline first, 1v1 body-swap gacha** proof of concept, bundled with three user-provided images: **Android 18 (Goku)**, **Videl (Goku)**, **Bulma (Goku)**. All values are **prototype balancing numbers**, not canon franchise power levels.

## Play

- **Cards**: Open the image-front, flip to a separate gameplay back; inspect mind attempts, body restrictions, passives and new skills.
- **Battle**: Select a fighter and opponent; use basic attacks, defense, learned and discovered skills. Energy and cooldowns matter; strained abilities cause recoil.
- **Progression**: Earn credits, train cards to unlock more discovered moves at level 2–3.
- **Summon**: Offline simulated gacha with earned currency; duplicate pulls yield shards. Test version gives all initial cards immediately.
- **Updates**: Import `soulswap-card-update` JSON packs (schemaVersion `1.0`) or a legacy card collection array with `mind`, `body`, `imageBase64`, `prompt`. New cards are unlocked for testing automatically. Updates persist in IndexedDB, with progress stored in localStorage. The engine generates deterministic fallback stats for new pairings; add authored stats in the pack for high-quality balance.

## Build Android debug APK

Push sources to GitHub and use the **Build SoulSwap Android APK** Actions workflow, or run `gradle :app:assembleDebug` with Java 17, Gradle 8.10.2 and Android SDK 35. Output is `app/build/outputs/apk/debug/app-debug.apk`.

No network permission, billing libraries or accounts are required. App works fully offline. Source uses only Android WebView and static HTML/CSS/JS assets.

## Update pack format

```
{
  "type": "soulswap-card-update",
  "schemaVersion": "1.0",
  "cards": [
    {
      "mind": "Son Goku (Dragon Ball)",
      "body": "Android 18 (Dragon Ball)",
      "imageBase64": "data:image/jpeg;base64,...",
      "rarity": "legendary",
      "variant": "Limitless Engine",
      "flavor": "Same fighting spirit. A body built to keep going."
    }
  ]
}
```

Supported optional fields: `id`, `name`, `power`, `hp`, `str`, `spd`, `end`, `dex`, `eng`, `focus`, `sync`, `passive`, `limitation`, `lore`, `abilities`, `prompt`. `abilities` entries take `id`, `name`, `source`, `description`, `manifestation`, `kind`, `scale`, `cost`, `cooldown`, `unlock` (minimum level).

Original input files and backgrounds are for this user's private franchise-fan prototype; distribution/commercial use of licensed characters/art may require authorization.


## v0.2 — Starter Summons, Settings and Random Matchmaking

Fresh installations start with an **empty owned collection** and 0 currency. Open **Soul Summon** for a one-time free 10-pull containing **at least one SR (Epic) or higher** card; duplicates become shards. Normal paid summons use earned test tokens. JSON card imports add cards to the *pool*, not to player ownership.

**Settings → Restart Game** resets player progress and the one-time starter claim with a typed `RESTART` confirmation. By default installed JSON packs remain available in the summon pool; optionally remove them too. Older v0.1 saves are preserved on upgrade until you deliberately restart.

**1v1 Arena:** an opponent is randomly chosen from installed cards. Opponent level is 95% normal (your level ±2) or 5% elite (+3 to +10), clamped to level 1–25. Elite encounters are announced before your first action. If only one card is installed, mirror matches are allowed.

Debug APK: GitHub Actions → `Build SoulSwap Android APK` → artifact named `SoulSwap-0.2-debug-apk`.
