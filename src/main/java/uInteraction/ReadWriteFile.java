package uInteraction;

import data.Department;
import data.Employee;
import errors.MyException;
import errors.Response;
import logic.Util;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReadWriteFile {

    private static final String FILENAMEREAD = "text.txt";

    private static final String FILENAMEWRITE = "result.txt";

    private Map<String, Department> departments = new HashMap<String, Department>();

    public void start() {
        try {
            Integer i = Integer.valueOf("3");
            readFile();
            writeFile();
        } catch (MyException e) {
            System.out.println(e.getResponse().getErrorCode() + " " + e.getResponse().getErrorMessage());
        }
    }

    private void readFile() {
        try {
            InputStream inputStream = new FileInputStream(FILENAMEREAD);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] s = sCurrentLine.split(";");
                if (!departments.containsKey(s[2])) {
                    departments.put(s[2], new Department());
                }
                departments.get(s[2]).getEmployeeList().add(new Employee(s[0], s[1], new BigInteger(s[3])));
            }
        } catch (NumberFormatException e) {
            throw new MyException(Response.NUBER_FORMAT_EXCEPTION);
        } catch (UnsupportedEncodingException e) {
            throw new MyException(Response.INVALID_INPUT_DATA);
        } catch (IOException e) {
            throw new MyException(Response.FILE_NOT_FOUND);
        }
    }

//    private void readFile() {
//        try (BufferedReader br = new BufferedReader(new FileReader(FILENAMEREAD))) {
//            String sCurrentLine;
//            while ((sCurrentLine = br.readLine()) != null) {
//                String[] s = sCurrentLine.split(";");
//                if (!departments.containsKey(s[2])) {
//                    departments.put(s[2], new Department());
//                }
//                departments.get(s[2]).getEmployeeList().add(new Employee(s[0], s[1], new BigInteger(s[3])));
//            }
//        } catch (IOException e) {
//            throw new MyException(Response.FILE_NOT_FOUND);
//        } catch (NumberFormatException e) {
//            throw new MyException(Response.INVALID_INPUT_DATA);
//        }
//    }

    private void writeFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(FILENAMEWRITE, "UTF-8");
        } catch (FileNotFoundException e) {
            throw new MyException(Response.RESULT_FILE_NOT_FOUND);
        } catch (UnsupportedEncodingException e) {
            throw new MyException(Response.UNSUPPORTED_ENCODING_EXCEPTION);
        }
        for (Map.Entry<String, Department> departmentEntryFrom : departments.entrySet()) {
            List<Employee> fromList = departmentEntryFrom.getValue().getEmployeeList();
            BigInteger fromAvgSalary = Util.avgSalary(fromList);
            writer.println("Из отдела " + departmentEntryFrom.getKey());
            for (Map.Entry<String, Department> departmentEntryTo : departments.entrySet()) {
                if (!departmentEntryFrom.getKey().equals(departmentEntryTo.getKey())) {
                    List<Employee> toTheList = departmentEntryTo.getValue().getEmployeeList();
                    BigInteger toTheAvgSalary = Util.avgSalary(toTheList);
                    writer.print("\nВ отдел " + departmentEntryTo.getKey() + ":");
                    List<List<Employee>> result = Util.combination(fromList.size() - 1, new ArrayList<List<Employee>>(), fromList);
                    if (result.size() != 0) {
                        for (List<Employee> listCombination : result) {
                            if (Util.comparisonSalary(fromAvgSalary, toTheAvgSalary, listCombination)) {
                                writer.print("\n");

                                for (Employee e : listCombination) {
                                    writer.print(" Name: " + e.getName() + ", Surname: " + e.getSurname() + ", "
                                            + "Salary: " + e.getSalary().toString() + ";");
                                }
                            }
                        }
                    }
                }
            }
            writer.print("\n\n\n");
        }
        writer.close();
    }
}
