package org.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import service.Service;
import validation.StudentValidator;
import validation.ValidationException;

import java.time.LocalDate;

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

    public void testAssignmentIDEmpty()
    {
        var service = Service.getService();
        Tema assignment = new Tema("", "Tema 1", 8,9);

        try {
            service.addTema(assignment);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Numar tema invalid!", e.getMessage());
        }
    }

    public void testAssignmentDescriptionEmpty()
    {
        var service = Service.getService();
        Tema assignment = new Tema("1", "", 8,9);

        try {
            service.addTema(assignment);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Descriere invalida!", e.getMessage());
        }
    }

    public void testAssignmentDeadlineInvalid()
    {
        var service = Service.getService();
        Tema assignment = new Tema("1", "a", -1,9);

        try {
            service.addTema(assignment);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Deadlineul trebuie sa fie intre 1-14.", e.getMessage());
        }
    }

    public void testAssignmentReceivedInvalid()
    {
        var service = Service.getService();
        Tema assignment = new Tema("1", "a", 2,-1);

        try {
            service.addTema(assignment);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Saptamana primirii trebuie sa fie intre 1-14.", e.getMessage());
        }
    }

    public void testAllAssignmentOk()
    {
        var service = Service.getService();
        Tema assignment = new Tema("1", "a", 2,3);

        try {
            service.addTema(assignment);
        } catch (ValidationException e) {
            fail("Expected no ValidationException to be thrown");
        }
    }

    // incremental integration testing
    public void testAddStudentIncremental()
    {
        var service = Service.getService();
        Student student = new Student("1", "Alex", 1, "email");

        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            fail("Expected no ValidationException to be thrown");
        }
    }

    public void testAddAssignmentIncremental()
    {
        var service = Service.getService();
        Tema assignment = new Tema("1", "tema 1", 2, 5);

        try {
            testAddStudentIncremental();
            service.addTema(assignment);
        } catch (ValidationException e) {
            fail("Expected no ValidationException to be thrown");
        }
    }

    public void testAddGradeIncremental()
    {
        var service = Service.getService();
        Nota nota = new Nota("1", "1", "1", 10, LocalDate.now());

        try {
            testAddStudentIncremental();
            testAddAssignmentIncremental();
            service.addNota(nota, "gg");
        } catch (ValidationException e) {

        }
    }



}
