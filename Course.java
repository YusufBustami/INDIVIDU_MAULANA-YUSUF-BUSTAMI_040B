
import java.util.Vector;
import java.util.Enumeration;

public class Course {
    private String title;
    private Vector<CourseRecord> courseRecords;

    public Course() {
        courseRecords = new Vector<CourseRecord>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String t) {
        title = t;
    }

    public void addCourseRecord(CourseRecord cr) {
        courseRecords.addElement(cr);
    }

    public Enumeration<CourseRecord> getCourseRecords() {
        return courseRecords.elements();
    }

    // Cetak seluruh mahasiswa yang mengambil course
    public void printCourseStudents() {
        Enumeration<CourseRecord> records = getCourseRecords();

        System.out.println("Mahasiswa yang mengambil " + title + ":");

        while (records.hasMoreElements()) {
            CourseRecord cr = records.nextElement();
            Student student = cr.getStudent();

            if (student != null) {
                System.out.println(student.getName());
            }
        }
    }

    // Cetak mahasiswa dengan nilai rata-rata terbaik
    public void printBestStudent() {
        Enumeration<CourseRecord> records = getCourseRecords();

        Student bestStudent = null;
        double bestAverage = -1;

        while (records.hasMoreElements()) {
            CourseRecord cr = records.nextElement();
            Student student = cr.getStudent();

            if (student == null) {
                continue;
            }

            Enumeration<Assignment> assignments = cr.getAssignments();

            double total = 0;
            int count = 0;

            while (assignments.hasMoreElements()) {
                Assignment a = assignments.nextElement();

                try {
                    total += a.getMark();
                    count++;
                } catch (NotYetSetException e) {
                    // Nilai yang belum diisi tidak dihitung
                }
            }

            if (count > 0) {
                double average = total / count;

                if (average > bestAverage) {
                    bestAverage = average;
                    bestStudent = student;
                }
            }
        }

        if (bestStudent != null) {
            System.out.println("Mahasiswa dengan nilai rata-rata terbaik:");
            System.out.println("Nama: " + bestStudent.getName());
            System.out.println("Rata-rata: " + bestAverage);
        } else {
            System.out.println("Belum ada nilai mahasiswa yang tersedia.");
        }
    }

    // Cetak mahasiswa yang layak ujian akhir
    public void printFinalExamStudents() {
        Enumeration<CourseRecord> records = getCourseRecords();

        System.out.println("Mahasiswa yang layak ujian akhir:");

        boolean found = false;

        while (records.hasMoreElements()) {
            CourseRecord cr = records.nextElement();
            Student student = cr.getStudent();

            if (student == null) {
                continue;
            }

            Enumeration<Assignment> assignments = cr.getAssignments();

            double total = 0;
            int count = 0;
            boolean allMarksSet = true;

            while (assignments.hasMoreElements()) {
                Assignment a = assignments.nextElement();

                try {
                    total += a.getMark();
                    count++;
                } catch (NotYetSetException e) {
                    allMarksSet = false;
                }
            }

            // Asumsi syarat ujian akhir: rata-rata minimal 60
            if (count > 0 && allMarksSet && total / count >= 60) {
                System.out.println(
                    student.getName() + " - Rata-rata: " + (total / count)
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("Belum ada mahasiswa yang memenuhi syarat.");
        }
    }
}
