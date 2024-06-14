package me.d1n3x3z7.fsip_mcplugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static me.d1n3x3z7.fsip_mcplugin.FSIP_MCPlugin.cprint;

public class SSHManager {

    private final String port;
    private Process proc = null;
    private String conn = null;

    public SSHManager(String port) {
        this.port = port;
    }

    public void open() throws IOException {
        if (proc == null) {
            String command = String.format("ssh -R 0:localhost:%s serveo.net", port);
            proc = Runtime.getRuntime().exec(command);
            cprint("--- SSH CONNECTED ---");
        } else {
            cprint("--- SSH ALREADY EXISTS ---");
        }
    }

    private String readSSH() throws IOException {
        if (proc != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            return reader.readLine();
        } else {
            return null;
        }
    }

    public String getConnect() throws IOException {
        if (proc != null & conn == null) {
            String[] carray = readSSH().split(" ");
            String connect = carray[carray.length - 1];
            cprint("NEW CONNECT - " + connect);
            conn = connect;
            return connect;
        } else if (proc != null & conn != null) {
            return conn;
        } else  {
            cprint("PROC is NULL");
            return null;
        }
    }

    public void close() {
        if (proc != null) {
            proc.destroy();
            conn = null;
            proc = null;
        }
        cprint("--- SSH CLOSED ---");
    }
}
