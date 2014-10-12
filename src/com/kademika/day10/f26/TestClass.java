package com.kademika.day10.f26;

@Service
public class TestClass {

    public TestClass() {
    }

    @Service
    public void simpleMethod() {
        System.out.println("Simple method");
    }

    @InitService
    public void printInfo() throws NoSuchMethodException {
        System.out.print(this.getClass().getMethod("printInfo").getName() + " - ");
        System.out.println("This method used annotation @InitService");
    }
}
