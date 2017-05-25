package oal.oracle.apps.epm.utils.Multithreading;

import java.io.File;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

import oal.oracle.apps.epm.entities.Order_Header;
import oal.oracle.apps.epm.entities.Order_Lines;

public class FairLock {
    public FairLock() {
        super();
    }
    
    public static void main(String[] args) throws ParseException {
        
        Order_Header oh=new Order_Header();
        Order_Lines ol=new Order_Lines();
        List<Order_Lines> olList=new ArrayList<>();
        Order_Lines ol_out=new Order_Lines();
        Order_Header oh_out=new Order_Header();
        
        
        System.out.println(oh_out.getOrderHeaderId());
        
    }
}
