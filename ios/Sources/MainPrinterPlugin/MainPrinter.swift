import Foundation

@objc public class MainPrinter: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
