./gradlew :app:bundleDebug

adb uninstall com.songify

pushd app/build/outputs/bundle/debug

rm app-debug.apks
# The --local-testing flag is required to simulate feature module installation
bundletool build-apks --local-testing --bundle app-debug.aab --output app-debug.apks
bundletool install-apks --apks app-debug.apks

popd
