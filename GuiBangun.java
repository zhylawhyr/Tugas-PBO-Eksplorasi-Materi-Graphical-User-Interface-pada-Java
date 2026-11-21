import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 1. ABSTRACT CLASS 
abstract class BangunDatar implements Informasi {
    protected String nama;

    public BangunDatar(String nama) {
        this.nama = nama;
    }

    public abstract double hitungLuas();
    public abstract double hitungKeliling();

    // Isi method dari interface Informasi
    @Override
    public String ambilInfo() {
        return nama + "\nLuas = " + hitungLuas() + "\nKeliling = " + hitungKeliling();
    }

    public void tampilkanInfo() {
        System.out.println("Bangun   : " + nama);
        System.out.println("Luas     : " + hitungLuas());
        System.out.println("Keliling : " + hitungKeliling());
        System.out.println("-----------------------------");
    }
}

class Persegi extends BangunDatar {
    private double sisi;

    public Persegi(double sisi) {
        super("Persegi");
        this.sisi = sisi;
    }

    @Override
    public double hitungLuas() {
        return sisi * sisi;
    }

    @Override
    public double hitungKeliling() {
        return 4 * sisi;
    }
}

class Lingkaran extends BangunDatar {
    private double jariJari;

    public Lingkaran(double jariJari) {
        super("Lingkaran");
        this.jariJari = jariJari;
    }

    @Override
    public double hitungLuas() {
        return 3.14 * jariJari * jariJari;
    }

    @Override
    public double hitungKeliling() {
        return 2 * 3.14 * jariJari;
    }
}

class PersegiPanjang extends BangunDatar {
    private double panjang, lebar;

    public PersegiPanjang(double panjang, double lebar) {
        super("Persegi Panjang");
        this.panjang = panjang;
        this.lebar = lebar;
    }

    @Override
    public double hitungLuas() {
        return panjang * lebar;
    }

    @Override
    public double hitungKeliling() {
        return 2 * (panjang + lebar);
    }
}


// 2. INTERFACE 
interface Informasi {
    String ambilInfo();
}


// ===================== 3. GUI =====================

public class GuiBangun extends JFrame implements ActionListener {   
    JTextField input1 = new JTextField();
    JTextField input2 = new JTextField();
    JButton tombolPersegi = new JButton("Persegi");
    JButton tombolLingkaran = new JButton("Lingkaran");
    JButton tombolPersegiPanjang = new JButton("Persegi Panjang");
    JLabel hasil = new JLabel("Hasil: -", SwingConstants.CENTER);
    JLabel status = new JLabel(" ", SwingConstants.CENTER);

    public GuiBangun() {
        setTitle("Bangun Datar");
        setSize(460, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));                  

        JPanel panelInput = new JPanel(new GridLayout(2, 2, 8, 8));   
        panelInput.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
        panelInput.add(new JLabel("Ukuran 1 (sisi / jari-jari / panjang):"));
        panelInput.add(input1);
        panelInput.add(new JLabel("Ukuran 2 (lebar):"));
        panelInput.add(input2);

        JPanel panelTombol = new JPanel(new FlowLayout());            
        panelTombol.add(tombolPersegi);
        panelTombol.add(tombolLingkaran);
        panelTombol.add(tombolPersegiPanjang);

        JPanel panelHasil = new JPanel(new BorderLayout());
        panelHasil.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panelHasil.add(hasil, BorderLayout.CENTER);
        panelHasil.add(status, BorderLayout.SOUTH);

        add(panelInput, BorderLayout.NORTH);
        add(panelTombol, BorderLayout.CENTER);
        add(panelHasil, BorderLayout.SOUTH);

        tombolPersegi.addActionListener(this);
        tombolLingkaran.addActionListener(this);
        tombolPersegiPanjang.addActionListener(this);

        MouseAdapter hover = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton b = (JButton) e.getSource();
                status.setText("Hitung " + b.getText());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                status.setText(" ");
            }
        };
        tombolPersegi.addMouseListener(hover);
        tombolLingkaran.addMouseListener(hover);
        tombolPersegiPanjang.addMouseListener(hover);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double a = Double.parseDouble(input1.getText());

            BangunDatar b;
            if (e.getSource() == tombolPersegi) {
                b = new Persegi(a);
            } else if (e.getSource() == tombolLingkaran) {
                b = new Lingkaran(a);
            } else {
                double l = Double.parseDouble(input2.getText());
                b = new PersegiPanjang(a, l);
            }

            Informasi info = b;
            hasil.setText("<html><center>" + info.ambilInfo().replace("\n", "<br>")
                    + "</center></html>");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!");
        }
    }

    public static void main(String[] args) {
        new GuiBangun().setVisible(true);
    }
}