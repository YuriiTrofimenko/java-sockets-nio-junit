/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tyaa.testsocketclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Юрий
 */
public class TestSocketClient {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        //Переменная для клиентского сокета
        Socket socket;
        //Перменные для потоков ввода и вывода
        InputStream inputStream = null;
        OutputStream outputStream = null;
        //Переменные для высокоуровневых оберток потоков ввода и вывода
        OutputStreamWriter outputStreamWriter = null;
        InputStreamReader inputStreamReader;

        BufferedWriter bufferedWriter;
        BufferedReader bufferedReader;
        //Переменная для строки-ответа сервера
        String responseString = null;

        try {
            //Создаем клиентский сокет с указанием IP-адреса,
            // по которому будет отправляться запрос, и номера порта,
            // на котором слушает сервер
            socket = new Socket("localhost", 3000);
            //Инициализируем потоки ввода и вывода
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Error1");
        }
        //Инициализируем обертки потоков ввода-вывода
        try {
            outputStreamWriter = new OutputStreamWriter(outputStream, "utf8");
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Error2");
        }
        bufferedWriter = new BufferedWriter(outputStreamWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter, true);

        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        Scanner sc = new Scanner(System.in);
        
        try {
            while (true) {
                
                System.out.print("> ");
                String commString = sc.next();
                //Отправляем серверу строку-запрос
                //printWriter.println("hello" + (Math.random() * 100));
                //printWriter.println("stop-domain");
                printWriter.println(commString);
                //Читаем потоком ввода строку-ответ сервера
                responseString = bufferedReader.readLine();
                System.out.println("Server: " + responseString);
                if (commString.equals("stop-domain")) {
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("Error3");
        }
    }
    
}
