package com.example.uniscovery;

public class RespuestaTest {
    private float InteresC;
    private float InteresH;
    private float InteresA;
    private float InteresS;
    private float InteresI;
    private float InteresD;
    private float InteresE;
    private float AptitudC;
    private float AptitudH;
    private float AptitudA;
    private float AptitudS;
    private float AptitudI;
    private float AptitudD;
    private float AptitudE;

    float getInteresC() {
        return InteresC;
    }

    void setInteresC(float InteresC) {
        this.InteresC = InteresC;
    }

    float getInteresH() {
        return InteresH;
    }

    void setInteresH(float InteresH) { this.InteresH = InteresH;
    }

    float getInteresA() {
        return InteresA;
    }

    void setInteresA(float InteresA) {
        this.InteresA = InteresA;
    }

    float getInteresS() {
        return InteresS;
    }

    void setInteresS(float InteresS) {
        this.InteresS = InteresS;
    }

    float getInteresI() {
        return InteresI;
    }

    void setInteresI(float InteresI) {
        this.InteresI = InteresI;
    }

    float getInteresD() {
        return InteresD;
    }

    void setInteresD(float InteresD) {
        this.InteresD = InteresD;
    }

    float getInteresE() {
        return InteresE;
    }

    void setInteresE(float InteresE) {
        this.InteresE = InteresE;
    }

    float getAptitudC() {
        return AptitudC;
    }

    void setAptitudC(float aptitudC) { 
        AptitudC = aptitudC; }

    float getAptitudH() {
        return AptitudH;
    }

    void setAptitudH(float aptitudH) {
        AptitudH = aptitudH;
    }

    float getAptitudA() {
        return AptitudA;
    }

    void setAptitudA(float aptitudA) {
        AptitudA = aptitudA;
    }

    float getAptitudS() {
        return AptitudS;
    }

    void setAptitudS(float aptitudS) {
        AptitudS = aptitudS;
    }

    float getAptitudI() {
        return AptitudI;
    }

    void setAptitudI(float aptitudI) {
        AptitudI = aptitudI;
    }

    float getAptitudD() {
        return AptitudD;
    }

    void setAptitudD(float aptitudD) {
        AptitudD = aptitudD;
    }

    float getAptitudE() {
        return AptitudE;
    }

    void setAptitudE(float aptitudE) {
        AptitudE = aptitudE;
    }

    RespuestaTest() {
        InteresC =0;
        InteresH=0;
        InteresA=0;
        InteresS=0;
        InteresI=0;
        InteresD=0;
        InteresE=0;
        AptitudC=0;
        AptitudH=0;
        AptitudA=0;
        AptitudS=0;
        AptitudI=0;
        AptitudD=0;
        AptitudE=0;
    }
     RespuestaTest(RespuestaTest respuestas) {
        InteresC=respuestas.getInteresC();
        InteresH=respuestas.getInteresH();
        InteresA=respuestas.getInteresA();
        InteresS=respuestas.getInteresS();
        InteresI=respuestas.getInteresI();
        InteresD=respuestas.getInteresD();
        InteresE=respuestas.getInteresE();
        AptitudC=respuestas.getAptitudC();
        AptitudH=respuestas.getAptitudH();
        AptitudA=respuestas.getAptitudA();
        AptitudS=respuestas.getAptitudS();
        AptitudI=respuestas.getAptitudI();
        AptitudD=respuestas.getAptitudD();
        AptitudE=respuestas.getAptitudE();
    }

}
