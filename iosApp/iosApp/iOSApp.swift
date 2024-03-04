import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        NapierProxyKt.debugBuild()
        KoinProxyKt.koinInit()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
