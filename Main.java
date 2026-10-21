public class Main {
    public static void main(String[] args) {
        Course course = new Course();
        course.setTitle("Pemrograman Java");

        Student student = new Student();
        student.setName("Andi");

        CourseRecord record = new CourseRecord();
        record.setStudent(student);

        course.addCourseRecord(record);
        // Menambahkan nilai assignment
        Assignment a1 = new Assignment();
        a1.setMark(80);

        Assignment a2 = new Assignment();
        a2.setMark(90);

        record.addAssignment(a1);
        record.addAssignment(a2);

        // Menguji method Course
        System.out.println("\n=== DAFTAR MAHASISWA ===");
        course.printCourseStudents();

        System.out.println("\n=== MAHASISWA TERBAIK ===");
        course.printBestStudent();

        System.out.println("\n=== MAHASISWA LAYAK UJIAN AKHIR ===");
        course.printFinalExamStudents();
        System.out.println("Nama Mata Kuliah: " + course.getTitle());
        System.out.println("Nama Mahasiswa: "
                + record.getStudent().getName());
    }
}