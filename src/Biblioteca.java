import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

/**
 * Clasa Biblioteca reprezintă o aplicație GUI pentru gestionarea unei biblioteci.
 * Permite căutarea, afișarea detaliilor, împrumutarea și returnarea cărților.
 */
public class Biblioteca extends JFrame {
    /**
     * Lista de cărți disponibile în bibliotecă.
     */
    private List<Book> books = new ArrayList<>();

    /**
     * Lista de cărți împrumutate.
     */
    private List<Book> loanedBooks = new ArrayList<>();

    /**
     * Inițializează și afișează fereastra principală a aplicației.
     *
     * Această metodă configurează fereastra principală a aplicației, setând dimensiunea,
     * titlul, comportamentul la închidere și alte proprietăți. De asemenea, populează
     * lista de cărți și afișează interfața grafică utilizatorului.
     *
     * Detalii:
     * - Dimensiunea ferestrei: 900x600 pixeli
     * - Titlu: "Biblioteca"
     * - Fereastră non-redimensionabilă (caracteristică specifică pentru macOS)
     * - Fereastra este centrată pe ecran
     *
     * Notă: Managerul de layout este setat la null, ceea ce înseamnă că poziționarea
     * componentelor trebuie realizată manual.
     */
    public void createAndShowGUI() {
        populateBooks();
        setSize(900, 600);
        setTitle("Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // feature pentru apple(MacOS)
        setVisible(true);

        setLayout(null);
/**
 * Creează și configurează panoul de căutare pentru interfața grafică.
 * Panoul de căutare permite utilizatorului să caute cărți în funcție de criterii
 * precum titlul, autorul, anul sau editura. Include un câmp de text pentru introducerea
 * termenilor de căutare, un buton pentru inițierea căutării și un tabel pentru afișarea
 * rezultatelor.
 * Componente:
 * - JLabel pentru afișarea textului "Filtru".
 * - JComboBox pentru selectarea criteriului de căutare (Titlu, Autor, An, Editura).
 * - JTextField pentru introducerea termenului de căutare.
 * - JButton pentru inițierea căutării.
 * - JTable pentru afișarea rezultatelor căutării, cu coloane: Titlu, Autor, An, Editura.
 * Detalii de poziționare:
 * - Panoul de căutare este poziționat în partea de sus a ferestrei, având dimensiunea
 *   880x150 pixeli.
 * - Componentele sunt poziționate manual folosind coordonate absolute.
 * Notă: Tabelul de rezultate este ne-editabil.
 */
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setBounds(10, 10, 880, 150);
        add(searchPanel);

        JLabel filter = new JLabel("Filtru");
        filter.setBounds(10, 10, 100, 25);
        searchPanel.add(filter);

        String[] criterii = {"Titlu", "Autor", "An", "Editura"};
        JComboBox<String> cmbCriterii = new JComboBox<>(criterii);
        cmbCriterii.setBounds(50, 10, 150, 25);
        searchPanel.add(cmbCriterii);

        JTextField searchText = new JTextField();
        searchText.setBounds(220, 10, 200, 25);
        searchPanel.add(searchText);

        JButton searchButton = new JButton("Cauta");
        searchButton.setBounds(440, 10, 100, 25);
        getRootPane().setDefaultButton(searchButton);
        searchPanel.add(searchButton);

        JTable searchResultTable = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Titlu", "Autor", "An", "Editura"}
        ));
        JScrollPane searchResultScrollPane = new JScrollPane(searchResultTable);
        searchResultTable.setDefaultEditor(Object.class, null);
        searchResultScrollPane.setBounds(10, 50, 860, 90);
        searchPanel.add(searchResultScrollPane);

        /*
         * Panou detalii carte
         */
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(null);
        detailsPanel.setBounds(10, 170, 400, 380);

        JLabel labelTitlu = new JLabel("Titlu");
        labelTitlu.setBounds(10, 10, 80, 25);
        detailsPanel.add(labelTitlu);

        JTextField textTitlu = new JTextField();
        textTitlu.setBounds(100, 10, 280, 25);
        textTitlu.setEditable(false);
        detailsPanel.add(textTitlu);

        JLabel labelAutor = new JLabel("Autor");
        labelAutor.setBounds(10, 50, 80, 25);
        detailsPanel.add(labelAutor);

        JTextField textAutor = new JTextField();
        textAutor.setBounds(100, 50, 280, 25);
        textAutor.setEditable(false);
        detailsPanel.add(textAutor);

        JLabel labelPret = new JLabel("Pret");
        labelPret.setBounds(10, 90, 80, 25);
        detailsPanel.add(labelPret);

        JTextField textPret = new JTextField();
        textPret.setBounds(100, 90, 280, 25);
        textPret.setEditable(false);
        detailsPanel.add(textPret);

        JLabel labelSummary = new JLabel("Rezumat");
        labelSummary.setBounds(10, 130, 80, 25);
        detailsPanel.add(labelSummary);

        JTextArea textSummary = new JTextArea();
        textSummary.setBounds(100, 130, 280, 200);
        textSummary.setLineWrap(true);
        textSummary.setWrapStyleWord(true);
        textSummary.setEditable(false);
        detailsPanel.add(textSummary);

        add(detailsPanel);

        /*
         * Panou împrumuturi
         */
        JPanel loanPanel = new JPanel();
        loanPanel.setLayout(null);
        loanPanel.setBounds(420, 170, 470, 380);

        JLabel labelLoanBooks = new JLabel("Carti imprumutate");
        labelLoanBooks.setBounds(10, 10, 150, 25);
        loanPanel.add(labelLoanBooks);

        JTable tableLoanedBooks = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Titlu", "Autor", "Data Imprumut"}
        ));
        JScrollPane loanTableScroll = new JScrollPane(tableLoanedBooks);
        tableLoanedBooks.setDefaultEditor(Object.class, null);
        loanTableScroll.setBounds(10, 50, 450, 250);
        loanPanel.add(loanTableScroll);

        JButton buttonBorrow = new JButton("Imprumuta");
        buttonBorrow.setBounds(10, 305, 120, 25);
        loanPanel.add(buttonBorrow);

        JButton buttonReturn = new JButton("Returneaza");
        buttonReturn.setBounds(150, 305, 120, 25);
        loanPanel.add(buttonReturn);

        add(loanPanel);

        revalidate();
        repaint();

/**
 * Adaugă funcționalitate pentru butonul de căutare și selecția din tabelul de rezultate.
 * - La apăsarea butonului de căutare, se preia criteriul selectat și textul introdus,
 *   iar metoda `searchBooks` este apelată pentru a afișa rezultatele în tabel.
 * - La selectarea unui rând din tabel, metoda `displayBookDetails` este apelată pentru
 *   a afișa detaliile cărții selectate.
 */
        searchButton.addActionListener(e -> {
            String selectedCriteria = cmbCriterii.getSelectedItem().toString();
            String searchTextValue = searchText.getText().trim();
            searchBooks(selectedCriteria, searchTextValue, searchResultTable);
        });

        searchResultTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = searchResultTable.getSelectedRow();
            if (selectedRow != -1) {
                String titlu = searchResultTable.getValueAt(selectedRow, 0).toString();
                displayBookDetails(titlu, textTitlu, textAutor, textPret, textSummary);
            }
        });

        /**
         * Adaugă funcționalitate pentru butoanele de împrumut și returnare a cărților.
         * - La apăsarea butonului de împrumut (`buttonBorrow`), se verifică dacă este selectat
         *   un rând din tabelul de rezultate (`searchResultTable`). Dacă da, metoda `borrowBook`
         *   este apelată pentru a împrumuta cartea selectată. În caz contrar, se afișează un mesaj
         *   de avertizare.
         * - La apăsarea butonului de returnare (`buttonReturn`), se verifică dacă este selectat
         *   un rând din tabelul de cărți împrumutate (`tableLoanedBooks`). Dacă da, metoda `returnBook`
         *   este apelată pentru a returna cartea selectată. În caz contrar, se afișează un mesaj
         *   de avertizare.
         */

        buttonBorrow.addActionListener(e -> {
            int selectedRow = searchResultTable.getSelectedRow();
            if (selectedRow != -1) {
                String titlu = searchResultTable.getValueAt(selectedRow, 0).toString();
                borrowBook(titlu, tableLoanedBooks);
            } else {
                JOptionPane.showMessageDialog(null, "Selectati o carte din tabel!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        buttonReturn.addActionListener(e -> {
            int selectedRow = tableLoanedBooks.getSelectedRow();
            if (selectedRow != -1) {
                String titlu = tableLoanedBooks.getValueAt(selectedRow, 0).toString();
                returnBook(titlu, tableLoanedBooks);
            } else {
                JOptionPane.showMessageDialog(null, "Selectati o carte de returnat!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    /**
     * Populează lista de cărți disponibile în bibliotecă.
     */
    private void populateBooks() {
        books.add(new Book("Zece negrii mititei", "Agatha Christie", 1939, "Collins Crime Club", 45.99, "Zece străini sunt invitați pe o insulă izolată și, pe măsură ce fiecare este acuzat de o crimă din trecut, încep să dispară unul câte unul. Suspansul crește, iar vinovatul este dezvăluit abia la final. O capodoperă a genului polițist.", null));
        books.add(new Book("Tăcerea mieilor", "Thomas Harris", 1948, "St. Martin's Press", 39.99, "Agenta FBI Clarice Starling investighează cazul unui criminal în serie, Buffalo Bill, apelând la ajutorul lui Hannibal Lecter, un psihiatru strălucit și criminal în serie. Un thriller psihologic intens.", null));
        books.add(new Book("Fata din tren", "Paula Hawkins", 2015, "Riverhead Books", 40.99, "Rachel, o femeie cu o viață dezordonată, asistă la ceva misterios în timp ce merge zilnic cu trenul. Pe măsură ce devine implicată într-un caz de dispariție, descoperă adevăruri tulburătoare despre cei din jur.", null));
        books.add(new Book("Sherlock Holmes: Câinele din Baskerville", "Arthur Conan Doyle", 1902, "George Newnes Ltd.", 50.99, " Detectivul Sherlock Holmes investighează legenda unui câine diabolic care bântuie familia Baskerville. Un mister captivant plin de întorsături de situație și atmosferă gotică.", null));
        books.add(new Book("Cei care merită să moară", "Michael Koryta", 2014, "Little, Brown and Company", 89.99, "Un adolescent martor la o crimă este urmărit de doi asasini. Aflat într-un program de protecție, găsește ajutor la o femeie care administrează o cabană izolat. Suspans și tensiune pe muchie de cuțit.", null));
    }

    /**
     * Caută cărți în funcție de criteriul și valoarea specificată.
     *
     * @param criteria    Criteriul de căutare (Titlu, Autor, An, Editura).
     * @param value       Valoarea căutată.
     * @param resultTable Tabelul în care se afișează rezultatele căutării.
     */
    private void searchBooks(String criteria, String value, JTable resultTable) {
        DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
        model.setRowCount(0);

        for (Book book : books) {
            if ((criteria.equals("Titlu") && book.getTitle().toLowerCase().contains(value.toLowerCase())) ||
                    (criteria.equals("Autor") && book.getAuthor().toLowerCase().contains(value.toLowerCase())) ||
                    (criteria.equals("An") && String.valueOf(book.getYear()).equals(value)) ||
                    (criteria.equals("Editura") && book.getPublisher().toLowerCase().contains(value.toLowerCase()))) {
                model.addRow(new Object[]{book.getTitle(), book.getAuthor(), book.getYear(), book.getPublisher()});
            }
        }
    }

    /**
     * Afișează detaliile unei cărți selectate.
     *
     * @param title       Titlul cărții selectate.
     * @param textTitlu   Câmpul text pentru titlu.
     * @param textAutor   Câmpul text pentru autor.
     * @param textPret    Câmpul text pentru preț.
     * @param textSummary Câmpul text pentru rezumat.
     */
    private void displayBookDetails(String title, JTextField textTitlu, JTextField textAutor, JTextField textPret,
                                    JTextArea textSummary) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                textTitlu.setText(book.getTitle());
                textAutor.setText(book.getAuthor());
                textPret.setText(String.format("%.2f RON", book.getPrice()));
                textSummary.setText(book.getSummary());
                break;
            }
        }
    }

    /**
     * Împrumută o carte selectată.
     *
     * @param title     Titlul cărții de împrumutat.
     * @param loanTable Tabelul în care se afișează cărțile împrumutate.
     */
    private void borrowBook(String title, JTable loanTable) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !loanedBooks.contains(book)) {
                loanedBooks.add(book);
                DefaultTableModel model = (DefaultTableModel) loanTable.getModel();
                model.addRow(new Object[]{book.getTitle(), book.getAuthor(), LocalDate.now()});
                JOptionPane.showMessageDialog(null, "Cartea a fost imprumutata cu succes!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Cartea nu poate fi imprumutata!", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Returnează o carte împrumutată.
     *
     * @param title     Titlul cărții de returnat.
     * @param loanTable Tabelul în care se afișează cărțile împrumutate.
     */
    private void returnBook(String title, JTable loanTable) {
        loanedBooks.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        DefaultTableModel model = (DefaultTableModel) loanTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equalsIgnoreCase(title)) {
                model.removeRow(i);
                break;
            }
        }
        JOptionPane.showMessageDialog(null, "Cartea a fost returnata!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}