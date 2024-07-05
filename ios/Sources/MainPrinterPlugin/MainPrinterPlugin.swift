import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(MainPrinterPlugin)
public class MainPrinterPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "MainPrinterPlugin"
    public let jsName = "MainPrinter"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "echo", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = MainPrinter()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }

        @objc func imprimir(_ call: CAPPluginCall) {
        guard let ipAddress = call.getString("ipAddress"),
              let data = call.getString("data") else {
            call.reject("Missing parameters")
            return
        }

         let port = call.getInt("port") ?? 9100 // Puerto predeterminado para muchas impresoras de red

        var outputStream: OutputStream? = nil
        Stream.getStreamsToHost(withName: ipAddress, port: port, inputStream: nil, outputStream: &outputStream)

        guard let outStream = outputStream else {
            call.reject("Failed to create output stream")
            return
        }

        outStream.open()
        let bytes = [UInt8](data.utf8)
        outStream.write(bytes, maxLength: bytes.count)
        outStream.close()

        call.resolve()
    }
}
