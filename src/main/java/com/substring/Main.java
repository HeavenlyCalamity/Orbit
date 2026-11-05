package com.substring;

import com.substring.service.ChatHelper;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        IO.println("----- Welcome To Chatbot -----");
        IO.println("Start Conversation with Chatbot.....");
        IO.println("""
                Press Quit to exit the conversation\n\n
                """);
        while(true){
            String readln = IO.readln("You: ");
            if(readln.trim().equalsIgnoreCase("quit")){
                IO.println("ChatBot: Bye! Have a nice day");
                break;
            }
            String response = ChatHelper.getResponse(readln);
            IO.println("ChatBot: " + response);
        }

    }
}
