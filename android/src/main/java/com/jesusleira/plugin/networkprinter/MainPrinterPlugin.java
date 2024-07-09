package com.jesusleira.plugin.networkprinter;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import android.util.Log;
import android.widget.Toast;

import java.io.OutputStream;
import java.net.Socket;
import com.print.Print;
import android.content.Context;

@CapacitorPlugin(name = "MainPrinter")
public class MainPrinterPlugin extends Plugin {
    private static Context mContext;

    private MainPrinter implementation = new MainPrinter();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void imprimir(PluginCall call) {
        String ipAddress = call.getString("ip");
        int port = call.getInt("puerto", 9100);
        String data = call.getString("dataaimprimir");
        Log.e("imprimir ip",ipAddress);
        Log.e("imprimir port", String.valueOf(port));
        Log.e("imprimir data",data);


        try (Socket socket = new Socket(ipAddress, port);
             OutputStream outputStream = socket.getOutputStream()) {
            outputStream.write(data.getBytes());
            outputStream.flush();
            call.resolve();
        } catch (Exception e) {
            call.reject("Failed to print", e);
        }
    }

    @PluginMethod
    public void imprimirWithJar(PluginCall call) {
       String ipAddress = call.getString("ip");
       String data = call.getString("dataaimprimir");
        Log.e("imprimirwj ip",ipAddress);
        Log.e("imprimirwj data",data);


       try {
           Print.PortClose();
           boolean opened = Print.IsOpened();
           if(!opened){
               openPort(ipAddress);
           }
           Print.PrintText("TURNO: ", 0, 48, 136);
           Print.PrintText(" ", 0, 48, 136);
           Print.PrintText(data, 1, 48, 68);
           Print.PrintText(" ", 0, 48, 136);
           Print.PrintText("ANALIZAR LABORATORIO CLINICO", 0, 0, 136);

           Print.CutPaper(1, 1);
           Print.PrintAndReturnStandardMode();
       } catch (Exception e) {
           Log.e("error en wj","mostrar error");

           e.printStackTrace();
       }  call.reject("Failed to print");
   }

    public static void openPort( final String ipImpresora){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    if (Print.PortOpen(mContext, "WiFi,"+ipImpresora+",9100") != 0){
                        Log.e("PortOpen","No conecta");
                    }else{
                        Log.e("PortOpen","conecta");
                    }
                }catch (Exception e){
                     Log.e("PortOpen","No conecta en el cacth");
                    e.printStackTrace();
                }

            }
        }.start();
    }





}
