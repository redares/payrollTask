package com.tenitskyDmitry.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tenitskyDmitry.model.DataStorage;
import com.tenitskyDmitry.model.Engineer;
import com.tenitskyDmitry.model.IEmployee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EngineerSalaryServlet", urlPatterns = "/calculateEngineerSalary")
public class EngineerSalaryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);

        int baseSalary = data.get("baseSalary").getAsInt();
        int bonus = data.get("bonus").getAsInt();

        IEmployee employee = new Engineer(baseSalary, bonus);
        DataStorage.addEmployee(employee);

        String answer = employee.getSalary() + "";

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(answer);
    }
}