package org.example;

import domain.Student;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import service.Service;
import validation.StudentValidator;
import validation.ValidationException;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testIDEmpty()
    {
        var service = Service.getService();
        Student student = new Student("", "Andrei", 1, "email");

        try {
            service.addStudent(student);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Id incorect!", e.getMessage());
        }
    }

    public void testNameNull()
    {
        var service = Service.getService();
        Student student = new Student("1", null, 1, "email");

        try {
            service.addStudent(student);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Nume incorect!", e.getMessage());
        }
    }

    public void testNameEmpty()
    {
        var service = Service.getService();
        Student student = new Student("1", "", 1, "email");

        try {
            service.addStudent(student);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Nume incorect!", e.getMessage());
        }
    }

    public void testGroupNegative()
    {
        var service = Service.getService();
        Student student = new Student("1", "Andrei", -1, "email");

        try {
            service.addStudent(student);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Grupa incorecta!", e.getMessage());
        }
    }


    public void testEmailNull()
    {
        var service = Service.getService();
        Student student = new Student("1", "Andrei", 1, null);

        try {
            service.addStudent(student);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Email incorect!", e.getMessage());
        }
    }

    public void testEmailEmpty()
    {
        var service = Service.getService();
        Student student = new Student("1", "Andrei", 1, "");

        try {
            service.addStudent(student);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Email incorect!", e.getMessage());
        }
    }

    public void testAllOk()
    {
        var service = Service.getService();
        Student student = new Student("1", "Andrei", 1, "email");

        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            fail("Expected no ValidationException to be thrown");
        }
    }
}
