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
import java.util.Calendar;

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
        try {
            Print.PortClose();
            boolean opened = Print.IsOpened();
            if(!opened){
                openPort(ipAddress);

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {

                                try {
                                    Print.PrintText("TURNO: ", 0, 48, 136);
                                    Print.PrintText(" ", 0, 48, 136);
                                    Print.PrintText(data, 1, 48, 68);
                                    Print.PrintText(" ", 0, 48, 136);
                                    Print.PrintText("ANALIZAR LABORATORIO CLINICO", 0, 0, 136);
                                    Calendar calendar = Calendar.getInstance();
                                    Print.PrintText(calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                                            calendar.get(Calendar.MONTH)+1 + "/" + calendar.get(Calendar.YEAR) + " " +
                                            calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" +
                                            calendar.get(Calendar.SECOND), 0, 0, 136);
                                    Print.CutPaper(1, 1);
                                    Print.PrintAndReturnStandardMode();
                                    call.resolve();

                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                call.resolve();

                            }
                        }, 1000);
            }else{
                Print.PrintText("TURNO: ", 0, 48, 136);
                Print.PrintText(" ", 0, 48, 136);
                Print.PrintText("data to test", 1, 48, 68);
                Print.PrintText(data, 1, 48, 68);
                Print.PrintText(" ", 0, 48, 136);
                Print.PrintText("ANALIZAR LABORATORIO CLINICO", 0, 0, 136);
                Calendar calendar = Calendar.getInstance();
                Print.PrintText(calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                        calendar.get(Calendar.MONTH)+1 + "/" + calendar.get(Calendar.YEAR) + " " +
                        calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" +
                        calendar.get(Calendar.SECOND), 0, 0, 136);
                Print.CutPaper(1, 1);
                Print.PrintAndReturnStandardMode();
                call.resolve();
            }


        } catch (Exception e) {
            Log.e("error en wj","mostrar error");

            e.printStackTrace();
            call.reject("Failed to print");
        }
    }

    public void openPort( final String ipImpresora){
        Context contextM = getActivity();
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    if (Print.PortOpen(contextM.getApplicationContext(), "WiFi,"+ipImpresora+",9100") != 0){
                        Log.e("PortOpen","port open No conecta");
                    }else{
                        Log.e("PortOpen","port oen conecta");
                    }
                }catch (Exception e){
                    Log.e("PortOpen","No conecta en el cacth");
                    e.printStackTrace();
                }

            }
        }.start();
    }

    @PluginMethod
    public void initPrint(PluginCall call){
        Context contextM = getActivity();
        String ipAddress = call.getString("ip");
        try
        {
            if(Print.PortOpen(contextM.getApplicationContext(),"WiFi,"+ipAddress+",9100") != 0)
            {
                Log.e("WIFI", "no Conectada");
                return;
            }
            else{
                Log.e("WIFI", "Conectada");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("HPRTSDKSample", (new StringBuilder("Activity_Main --> mUsbReceiver ")).append(e.getMessage()).toString());
        }
    }
}
