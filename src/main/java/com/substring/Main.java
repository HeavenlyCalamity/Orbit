package com.substring;

import com.substring.dto.StudyPlan;
import com.substring.service.ChatHelper;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.TokenStream;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        IO.println("----- Welcome To Chatbot -----");
        IO.println("Start Conversation with Chatbot.....");
        Integer conversationId = new Random().nextInt(1000);
        IO.println("Press 1 to ask a question about Java programming language");
        IO.println("Press 2 to get a study plan for a specific topic");
        IO.println("""
                Press Quit to exit the conversation\n\n
                """);
        while(true){
            String readln = IO.readln("You: ");
            if(readln.trim().equalsIgnoreCase("quit")){
                IO.println("ChatBot: Bye! Have a nice day");
                break;
            }
            if(readln.equals("1")){
                IO.println("ChatBot: Hello! How can I assist you today?");
                TokenStream tokenStream = ChatHelper.getResponse(conversationId, readln);
                IO.print("ChatBot: " );
                CompletableFuture<ChatResponse> future = new CompletableFuture<>();
                tokenStream.onCompleteResponse(chatResponse -> {
                            IO.println();
                            future.complete(chatResponse);
                        })
                        .onPartialResponse(
                                IO::print
                        )
                        .onError(e-> {
                            IO.println("Error: " + e.getMessage());
                            future.completeExceptionally(e);
                        })
                        .start();

                future.join();
            }
            else if(readln.equals("2")){
                String topic = IO.readln("Enter the topic you want to learn: ");
                Optional<String> timeframe = Optional.of(IO.readln("Enter the timeframe in which you want to learn the topic: "));
                IO.println("ChatBot: Creating a study plan for " + topic + " in " + timeframe.orElse("6 months") + "...");
                TokenStream tokenStream = ChatHelper.getStudyPlan(conversationId,topic, timeframe);
                CompletableFuture<ChatResponse> future = new CompletableFuture<>();
                tokenStream.onCompleteResponse(chatResponse -> {
                    IO.println();
                    future.complete(chatResponse);
                        })
                        .onPartialResponse(
                                IO::print
                        )
                        .onError(e-> {
                            IO.println("Error: " + e.getMessage());
                            future.completeExceptionally(e);
                        })
                        .start();

                        future.join();
            }
            else{
                TokenStream tokenStream = ChatHelper.getResponse(conversationId, readln);
                IO.print("ChatBot: ");
                CompletableFuture<ChatResponse> future = new CompletableFuture<>();
                tokenStream.onCompleteResponse(chatResponse -> {
                            IO.println();
                            future.complete(chatResponse);
                        })
                        .onPartialResponse(
                                IO::print
                        )
                        .onError(e-> {
                            IO.println("Error: " + e.getMessage());
                            future.completeExceptionally(e);
                        })
                        .start();

                future.join();
            }

        }

    }
}
