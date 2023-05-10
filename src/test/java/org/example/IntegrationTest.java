package org.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import junit.framework.TestCase;
import service.Service;
import validation.ValidationException;

import java.time.LocalDate;

import static junit.framework.Assert.fail;

public class IntegrationTest
        extends TestCase {
    public void testAddStudent()
    {
        var service = Service.getService();
        Student student = new Student("1", "Alex", 1, "email");

        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            fail("Expected no ValidationException to be thrown");
        }
    }

    public void testAddAssignment()
    {
        var service = Service.getService();
        Tema assignment = new Tema("1", "tema 1", 2, 5);

        try {
            service.addTema(assignment);
        } catch (ValidationException e) {
            fail("Expected no ValidationException to be thrown");
        }
    }


    public void testAddGrade()
    {
        var service = Service.getService();
        Nota nota = new Nota("1", "1", "1", 10, LocalDate.now());

        try {
            service.addNota(nota, "gg");
        } catch (ValidationException e) {

        }
    }

    public void testBigBang()
    {
        try {
            testAddStudent();
            testAddAssignment();
            testAddGrade();
        } catch (ValidationException e) {
            fail("Expected an ValidationException to be thrown");
        }
    }


}
