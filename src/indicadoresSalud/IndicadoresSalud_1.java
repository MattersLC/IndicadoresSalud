package indicadoresSalud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DecimalFormat;

public class IndicadoresSalud_1 extends JFrame implements ActionListener {

    //private JPanel panel = new JPanel();
    private JButton cIMC; // Activa el cálculo del IMC
    private JButton cICC; // Activa el cálculo del ICC
    private JButton borrar; // Activa el borrado de los datos
    private JLabel resultadoIMC; // para mostrar el resultado del IMC
    private JLabel resultadoICC; // para mostrar el resultado del ICC
    private JTextField edad; // Recibe el dato descrito
    private JTextField estatura; // Recibe el dato descrito
    private JTextField peso; // Recibe el dato descrito
    private JTextField cadera; // Recibe el dato descrito
    private JTextField cintura; // Recibe el dato descrito
    private ButtonGroup opcion;
    private JRadioButton hombre; // Selección del dato descrito
    private JRadioButton mujer; // Selección del dato descrito

    public IndicadoresSalud_1() {
        getContentPane().setLayout(null);

        String path = "/imagenes/IMC.jpg"; // Ubicación de la imagen
        URL url = this.getClass().getResource(path); // Obtiene el recurso de la ubicación
        ImageIcon icon = new ImageIcon(url); // Crea un objeto ImageIcon a partir del recurso

        // Creación y construcción del título principal
        JLabel presentacion = new JLabel("INDICADORES BÁSICOS DE RIESGO A LA SALUD");//, icon, SwingConstants.CENTER);
        JLabel img = new JLabel(icon);
        // Construcción de los elementos a visualizar
        cIMC = new JButton("Calcular IMC");
        cICC = new JButton("Calcular ICC");
        edad = new JTextField(4);
        estatura = new JTextField(4);
        peso = new JTextField(4);
        resultadoIMC = new JLabel("_______________");
        cintura = new JTextField(4);
        cadera = new JTextField(4);
        hombre = new JRadioButton(" Hombre ");
        mujer = new JRadioButton(" Mujer ");
        resultadoICC = new JLabel("_______________");
        borrar = new JButton("Borrar datos");
        JLabel tEdad = new JLabel("Proporciona tu edad (>19):");
        JLabel tIMC = new JLabel("CÁLCULO DEL ÍNDICE DE MASA CORPORAL (IMC)");
        JLabel tEstatura = new JLabel("Estatura (mts):");
        JLabel tPeso = new JLabel("Peso (Kgs):");
        JLabel tICC = new JLabel("CÁLCULO DEL ÍNDICE DE CINTURA-CADERA (I-C-C)");
        JLabel tCintura = new JLabel("Cintura (cms):");
        JLabel tCadera = new JLabel("Cadera (cms):");
        JLabel tSexo = new JLabel("Sexo:");

        // Agregar elementos a visualizar
        presentacion.setBounds(10, 10, 300, 20);
        getContentPane().add(presentacion);
        img.setBounds(0, 20, 300, 180);
        getContentPane().add(img);
        tEdad.setBounds(50, 220, 300, 20);
        getContentPane().add(tEdad);
        edad.setBounds(210, 220, 40, 20);
        getContentPane().add(edad);
        tIMC.setBounds(5, 260, 300, 20);
        getContentPane().add(tIMC);
        tEstatura.setBounds(80, 290, 300, 20);
        getContentPane().add(tEstatura);
        estatura.setBounds(180, 290, 40, 20);
        getContentPane().add(estatura);
        tPeso.setBounds(80, 315, 300, 20);
        getContentPane().add(tPeso);
        peso.setBounds(180, 315, 40, 20);
        getContentPane().add(peso);
        cIMC.setBounds(10, 340, 105, 25);
        getContentPane().add(cIMC);
        resultadoIMC.setBounds(125, 340, 250, 25);
        getContentPane().add(resultadoIMC);
        tICC.setBounds(5, 370, 300, 20);
        getContentPane().add(tICC);
        tCintura.setBounds(80, 390, 300, 20);
        getContentPane().add(tCintura);
        cintura.setBounds(180, 390, 40, 20);
        getContentPane().add(cintura);
        tCadera.setBounds(80, 415, 300, 20);
        getContentPane().add(tCadera);
        cadera.setBounds(180, 415, 40, 20);
        getContentPane().add(cadera);
        tSexo.setBounds(50, 435, 40, 20);
        getContentPane().add(tSexo);
        hombre.setBounds(100, 435, 100, 20);
        hombre.setBackground(Color.WHITE);
        getContentPane().add(hombre);
        mujer.setBounds(200, 435, 100, 20);
        mujer.setBackground(Color.WHITE);
        getContentPane().add(mujer);
        cICC.setBounds(10, 465, 110, 20);
        getContentPane().add(cICC);
        resultadoICC.setBounds(130, 465, 170, 20);
        getContentPane().add(resultadoICC);
        borrar.setBounds(80, 510, 140, 30);
        getContentPane().add(borrar);

        opcion = new ButtonGroup();
        opcion.add(hombre);
        opcion.add(mujer);

        cIMC.addActionListener(this); // Función del botón para calcular el IMC
        cICC.addActionListener(this); // Función del botón para calcular el ICC
        borrar.addActionListener(this); // Función del botón para borrar los datos

        getContentPane().setBackground(Color.WHITE);
    }

    public void actionPerformed(ActionEvent ae) {
        // Convierte el objeto productor del evento a un objeto botón
        JButton btn = (JButton) ae.getSource();
        // Formato para sólo mostrar el resultado con dos décimales
        DecimalFormat df = new DecimalFormat("#.00");

        // Variables IMC
        String resIMC = ""; // Para el resultado a desplegar
        String sitIMC = ""; // Para la situación de acuerdo a la tabla
        boolean validarEstatura = estatura.getText().isEmpty();
        boolean validarPeso = peso.getText().isEmpty();

        // Variables ICC
        String resICC = ""; // Para el resultado a desplegar
        String sitICC = ""; // Para la situación de acuerdo a la tabla
        boolean validarCintura = cintura.getText().isEmpty();
        boolean validarCadera = cadera.getText().isEmpty();

        if(btn == cIMC && (!validarEstatura || !validarPeso)) { // Si el objeto que produce el evento es cIMC
            double mtsCms = Double.parseDouble(estatura.getText());
            double pesoKgs = Double.parseDouble(peso.getText());

            if (mtsCms < 1.40 || mtsCms > 2 || pesoKgs < 40 || pesoKgs > 160) {
                JOptionPane.showMessageDialog(this, "Se requieren valores dentro del rango");
            } else { // Calcular el valor del IMC
                double vIMC = pesoKgs / Math.pow(mtsCms, 2);
                if (vIMC > 0 && vIMC < 18.5) {
                    sitIMC = "Peso bajo";
                    resIMC = df.format(vIMC) + " - " + sitIMC;
                    resultadoIMC.setText(resIMC);
                } else if (vIMC >= 18.5 && vIMC <= 24.9) {
                    sitIMC = "Peso medio";
                    resIMC = df.format(vIMC) + " - " + sitIMC;
                    resultadoIMC.setText(resIMC);
                } else if (vIMC >= 25 && vIMC <= 29.9) {
                    sitIMC = "Sobre peso";
                    resIMC = df.format(vIMC) + " - " + sitIMC;
                    resultadoIMC.setText(resIMC);
                } else if (vIMC >= 30) {
                    sitIMC = "Obesidad";
                    resIMC = df.format(vIMC) + " - " + sitIMC;
                    resultadoIMC.setText(resIMC);
                }
            }
        } else if (btn == cICC && (!validarCintura || !validarCadera)) {
            double cint = Double.parseDouble(cintura.getText());
            double cad = Double.parseDouble(cadera.getText());

            if (cint < 0 || cad < 0) {
                JOptionPane.showMessageDialog(this, "Se requieren valores dentro del rango");
            } else { // Calcular el valor del ICC
                double vICC = cint / cad;
                if (hombre.isSelected()) {
                    if (vICC > 0 && vICC <= 0.95) {
                        sitICC = "Riesgo bajo";
                        resICC = df.format(vICC) + " - " + sitICC;
                        resultadoICC.setText(resICC);
                    } else if (vICC > 0.95 && vICC < 1) {
                        sitICC = "Riesgo medio";
                        resICC = df.format(vICC) + " - " + sitICC;
                        resultadoICC.setText(resICC);
                    } else if (vICC >= 1) {
                        sitICC = "Riesgo alto";
                        resICC = df.format(vICC) + " - " + sitICC;
                        resultadoICC.setText(resICC);
                    }
                } else if (mujer.isSelected()) {
                    if (vICC <= 0.80) {
                        sitICC = "Riesgo bajo";
                        resICC = df.format(vICC) + " - " + sitICC;
                        resultadoICC.setText(resICC);
                    } else if (vICC > 0.80 && vICC < 0.85) {
                        sitICC = "Riesgo medio";
                        resICC = df.format(vICC) + " - " + sitICC;
                        resultadoICC.setText(resICC);
                    } else if (vICC >= 0.85) {
                        sitICC = "Riesgo alto";
                        resICC = df.format(vICC) + " - " + sitICC;
                        resultadoICC.setText(resICC);
                    }
                }
            }
        } else if (btn == borrar && (!validarEstatura || !validarPeso || !validarCintura || !validarCadera)) {
            edad.setText(null);
            estatura.setText(null);
            peso.setText(null);
            resultadoIMC.setText("_______________");
            cintura.setText(null);
            cadera.setText(null);
            opcion.clearSelection();
            resultadoICC.setText("_______________");
        }
    }
}

class Prueba_1 {

    public static void main(String[] args) {
        IndicadoresSalud_1 is = new IndicadoresSalud_1();
        is.setVisible(true);
        is.setSize(310,600);
        is.setTitle("IMC-ICC");
        is.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        is.setLocationRelativeTo(null);
    }
}
