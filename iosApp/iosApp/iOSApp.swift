import SwiftUI
import shared

@main
struct iOSApp: App {

    init(){
        koinKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}