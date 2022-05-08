package com.example.practica4;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalculadoraActivity extends AppCompatActivity {

    /*
    Componentes de la calculadora
     */
    private TextView tvDisplay;
    private Button btnAc, btnMasmenos, btnPorcentaje, btnDiv, btnPor, btnMenos, btnMas, btnIgual, btnPunto;

    /*
    Creamos tres variables que guarden
    los números ingresados en la calculadora y la varieble res sera la
    repuesta guardada
     */
    private double num1 = 0.0, num2 = 0.0, res = 0.0;
    private int x = 0;
    private boolean operacion = false;

    private boolean enOperacion = false;
    private char seleccionOper;
    private int contadorPuntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);


        /*
        Ligamos los componentes de la  vista con este axctivity
        por medio del id y el método findViewById
         */

        tvDisplay = findViewById(R.id.tv_display);
        btnAc = findViewById(R.id.btn_ac);
        btnMasmenos = findViewById(R.id.btn_masmenos);
        btnPorcentaje = findViewById(R.id.btn_porcentaje);
        btnDiv = findViewById(R.id.btn_div);
        btnPor = findViewById(R.id.btn_por);
        btnMenos = findViewById(R.id.btn_menos);
        btnMas = findViewById(R.id.btn_mas);
        btnIgual = findViewById(R.id.btn_igual);
        btnPunto = findViewById(R.id.btn_punto);

        //Click Boton AC
        btnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDisplay.setText("0");
                num1 = 0.0;
                num2 = 0.0;
            }
        });

        //Click Más Menos
        btnMasmenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                masmenos();
            }
        });

        //Click Porcentaje
        btnPorcentaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                porcentaje();
            }
        });

        //Click Divición
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                divicion();
            }
        });

        //Click boton por
        btnPor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                por();
            }

        });

        //Click boton menos
        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menos();
            }
        });

        //Click boton mas
        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mas();
            }
        });

        //Click boton igual
        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (seleccionOper) {
                    case '&' :
                        masmenos();
                        break;
                    case '%' :
                        porcentaje();
                        break;
                    case '/' :
                        divicion();
                        break;
                    case '*' :
                        por();
                        break;
                    case '-' :
                        menos();
                        break;
                    case '+' :
                        mas();
                        break;
                }
            }
        });

        //Click boton punto
        btnPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder textdis = new StringBuilder( tvDisplay.getText().toString());
                for ( int i = 0; i < textdis.length(); i++) {
                    //Cuando se enceuntra el punto no se realiza ninguna acción
                    if ( textdis.charAt( i ) == '.' ) {
                        x = 1;
                    }
                }
                //Pone el punto si no encuentra algun otro punto
                if ( x != 1 ) {
                    textdis.append( '.' );
                    operacion = false;
                    tvDisplay.setText( textdis );
                }
            }
        });
    }

    /*
    Podemos agregar metodos genericos para ser utilizados por
    varios componentes, en este caso haremos un click genérico
    para todos los número
    Los métodos click genéricos deben ser void y contener un
    parámetro de tipo View
    Para indicar en la vista que se va a invocar a este
    metodo, utilizamos el atributo onclick
     */
    public void clickNumero(View componente) {
        //Tomamos el texto del display
        String textDisplay = tvDisplay.getText().toString();
        //Lo convertimos a un número
        double numeroDisplay = Double.parseDouble(textDisplay);

        Button seleccion = (Button) componente;
        //Al realizar el if no borra el punto si existe 0.
        if ( operacion == true && tvDisplay.getText().toString().compareTo("0.") != 0 ) {
            tvDisplay.setText( "" );
        }
        if ( numeroDisplay == 0 ) {
            tvDisplay.setText( "" );
        }

        String textoDisplay = tvDisplay.getText().toString();
        //Se concatena el valor mas la operación
        textoDisplay += seleccion.getText().toString();
        //Muestra el texto
        tvDisplay.setText(textoDisplay);

        operacion = false;
    }

    /*
        En este apartado se encontrarán las operaciones a realizar
        dependiendo el caso del switch
    */

    //Mas Menos
    private void masmenos() {
        //Tomamos el texto del display
        String textoDisplay = tvDisplay.getText().toString();

        //Convertimos a número
        double numeroDispolay = Double.parseDouble(textoDisplay);

        //Evaluamos si tenemos numeros guardados
        if (num1 == 0) {
            num1 = numeroDispolay;
        } else {
            num2 = numeroDispolay;
        }

        //Lo multiplicamos por -1
        numeroDispolay = numeroDispolay * -1;

        //Mostramos el texto
        tvDisplay.setText(String.valueOf(numeroDispolay));
    }

    //Mas Porcentaje
    private void porcentaje() {
        //Tomamos el texto del display
        String textoDisplay = tvDisplay.getText().toString();

        //Convertimos a número
        double numeroDispolay = Double.parseDouble(textoDisplay);

        //Evaluamos si tenemos numeros guardados
        seleccionOper = '%';
        if (num1 == 0) {
            num1 = numeroDispolay;
            operacion = true;
            res = num1/100;
        } else {
            num1 = numeroDispolay;
            operacion = true;
            res = num1/100;
        }

        //Mostramos el texto
        tvDisplay.setText(String.valueOf(res));

    }

    //Mas División
    private void divicion() {
        //Tomamos el texto del display
        String textoDisplay = tvDisplay.getText().toString();

        //Convertimos a número
        double numeroDispolay = Double.parseDouble(textoDisplay);

        //Evaluamos si tenemos numeros guardados
        seleccionOper = '/';
        if (num1 == 0) {
            num1 = numeroDispolay;
            operacion = true;
            res = num1;
        } else if (res != numeroDispolay) {
            operacion = true;
            num2 = numeroDispolay;
            try {
                res = num1/num2;
            }catch (Exception e) {
                Toast.makeText(this, "!Error! divición incorrecta", Toast.LENGTH_SHORT);
            }
        }

        //Mostramos el texto
        tvDisplay.setText(String.valueOf(res));

    }

    //Mas Multiplicar
    private void por() {
        //Tomamos el texto del display
        String textoDisplay = tvDisplay.getText().toString();

        //Convertimos a número
        double numeroDispolay = Double.parseDouble(textoDisplay);

        //Evaluamos si tenemos numeros guardados
        seleccionOper = '*';
        if (num1 == 0) {
            num1 = numeroDispolay;
            operacion = true;
            res = num1;
        } else if (res != numeroDispolay) {
            operacion = true;
            num2 = numeroDispolay;
            res = res * num2;
        }

        //Mostramos el texto
        tvDisplay.setText(String.valueOf(res));

    }

    //Mas Menos
    private void menos() {
        //Tomamos el texto del display
        String textoDisplay = tvDisplay.getText().toString();

        //Convertimos a número
        double numeroDispolay = Double.parseDouble(textoDisplay);

        //Evaluamos si tenemos numeros guardados
        seleccionOper = '-';
        if (num1 == 0) {
            num1 = numeroDispolay;
            operacion = true;
            res = num1;
        } else if (res != numeroDispolay) {
            operacion = true;
            num2 = numeroDispolay;
            res = res - num2;
        }

        //Mostramos el texto
        tvDisplay.setText(String.valueOf(res));

    }

    //Mas Mas
    private void mas() {
        //Tomamos el texto del display
        String textoDisplay = tvDisplay.getText().toString();

        //Convertimos a número
        double numeroDispolay = Double.parseDouble(textoDisplay);

        //Evaluamos si tenemos numeros guardados
        seleccionOper = '+';
        if (num1 == 0) {
            num1 = numeroDispolay;
            operacion = true;
            res = num1;
        } else if (res != numeroDispolay) {
            operacion = true;
            num2 = numeroDispolay;
            res = res + num2;
        }

        //Mostramos el texto
        tvDisplay.setText(String.valueOf(res));

    }

}