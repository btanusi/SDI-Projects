package com.galvanize;

import com.sun.org.apache.bcel.internal.classfile.SourceFile;

import java.sql.SQLOutput;

public class NameEmailFormatter {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("Please specify a name and email");
        } else if (args.length == 1){
            System.out.printf("Please specify an email for %s", args[0]);
        } else {
            System.out.printf("%s <%s>", args[0], args[1]);
        }
    }
}
