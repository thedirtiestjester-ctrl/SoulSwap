# SOULSWAP LONG-TERM APK UPDATES — SIGNING SETUP

The permanent signing keystore is PRIVATE and intentionally excluded from this repository, source ZIP, and GameBuild. Keep the key and password separately in safe storage.

Open: https://github.com/thedirtiestjester-ctrl/SoulSwap/settings/secrets/actions

Add two repository secrets from the private signing kit received in your ChatGPT conversation:
- SOULSWAP_KEYSTORE_BASE64: entire contents of GitHub_Secret_SOULSWAP_KEYSTORE_BASE64.txt
- SOULSWAP_KEYSTORE_PASSWORD: entire contents of GitHub_Secret_SOULSWAP_KEYSTORE_PASSWORD.txt

Then open Actions > Build SoulSwap Android APK > Run workflow. With both secrets configured it publishes SoulSwap-0.4.0-PERMANENT-SIGNED-APK. Never distribute UNSIGNED artifacts or debug builds with a randomly generated runner key. Future workflows must preserve the same keystore, package name com.soulswap.game and increment versionCode monotonically.

Before the first upgrade from v0.3.2: old builds have different signing certificates and Android cannot replace the existing installation without uninstall. Save progress may be recovered through Android Debug Bridge using the old debuggable APK; see MIGRATION.md. New v0.4.0 has Settings > Backup & Restore Save for future recoveries.
