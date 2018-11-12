/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.lib;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author andy
 */
public class SystemExec {
    public SystemExec(){
    
}
    public void Run(String Command){
        System.out.println("Running "+Command);
        StringBuffer output = new StringBuffer();
        try {
            Process p = Runtime.getRuntime().exec(Command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            output = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            System.out.println(output);
            reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            System.out.println(output);
             } catch (Exception et) {
            System.out.println("Can't run command" + et);
        }
    }
}
