package br.com.pjspring.ws.service;

public class ArquivoTexto {

    private String texto;



    public String dadosTexto(){

        texto = "BEGIN:VCARD\n" +
                "VERSION:3.0\n" +
                "PRODID:-//Apple Inc.//iOS 10.0.1//EN\n" +
                "N:;Amor ;;;\n" +
                "FN:Amor \n" +
                "EMAIL;type=INTERNET;type=HOME;type=pref:abertilho@gmail.com\n" +
                "TEL;type=CELL;type=VOICE;type=pref:+5562981883835\n" +
                "BDAY;value=date:1997-10-27\n" +
                "REV:2016-09-21T16:06:28Z\n" +
                "END:VCARD" +
                "BEGIN:VCARD\n" +
                "VERSION:3.0\n" +
                "PRODID:-//Apple Inc.//iOS 10.2//EN\n" +
                "N:;Ana  20/01 15:30;;;\n" +
                "FN:Ana  20/01 15:30\n" +
                "TEL;type=CELL;type=VOICE;type=pref:+55 62 8200-1555\n" +
                "REV:2017-01-19T15:44:33Z\n" +
                "END:VCARD" +
                "BEGIN:VCARD\n" +
                "VERSION:3.0\n" +
                "PRODID:-//Apple Inc.//iOS 9.3.2//EN\n" +
                "N:Calixtrato;Welson;;;\n" +
                "FN:Welson Calixtrato\n" +
                "TEL;type=CELL;type=VOICE;type=pref:981428182\n" +
                "REV:2016-05-30T02:22:42Z\n" +
                "END:VCARD";

        return texto;
    }



    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
