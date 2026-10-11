# ONE-TIME v0.3.2 → v0.4.0 SIGNING TRANSITION

Important: v0.3.2 and earlier APKs have ephemeral debug signatures. The new permanent key differs. Android does not allow these APKs to update each other. Normal reinstall of a different signer clears app-local data. DON'T UNINSTALL before exporting a backup.

Old v0.3.2 has no full save-export UI. If you need to preserve existing currency/cards, use a PC with Android Platform Tools (adb), enable USB debugging, and back up the app's private WebView directory through 'run-as'. Since old APKs are debug builds, run-as can access their app files.

With old app still installed:
  adb shell am force-stop com.soulswap.game
  adb exec-out run-as com.soulswap.game tar -cf - app_webview > SoulSwap_old_webview.tar

Verify the .tar archive contains app_webview before uninstall. Do not post this backup publicly; it holds your save and imported card content.

After installing the permanent-signed debug APK, restore via:
  adb shell am force-stop com.soulswap.game
  adb shell -T run-as com.soulswap.game tar -xf - < SoulSwap_old_webview.tar

If the directory is in use, force-stop the app and retry. This is an advanced optional recovery; Android versions differ and this process cannot be guaranteed. On future versions, use the built-in Settings > Export Complete Save Backup first, then Settings > Restore.

Keep the permanent signing key: with it, future versions install directly over each other without deleting local data.
