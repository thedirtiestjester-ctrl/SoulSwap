# SoulSwap v0.4.0

- Permanent test signing key via PRIVATE GitHub Actions secrets. Never commit private key.
- Preserve applicationId com.soulswap.game, increment versionCode 4 → 5.
- Settings > Export Complete Save Backup and Restore Selected Backup, including imported card packs.
- GitHub Actions with no signing secrets only publishes UNSIGNED artifact for private signing, never a misleading update APK.
- One-time legacy debug signature migration unavoidable; advanced ADB preservation instructions.
- Existing 15-card gameplay, SoulForge shards, starter summons, rarity overlays, JSON importer remain intact.
