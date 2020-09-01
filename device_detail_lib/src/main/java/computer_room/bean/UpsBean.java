package computer_room.bean;

import java.io.Serializable;

/**
 * UPS BEAN
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/14 09 : 57
 */
public class UpsBean implements Serializable {
    /**
     * id : 1
     * name : 1号UPS
     * address : 1
     * batVol : 230.0
     * batCur : 220.0
     * batTemp : 57.5
     * intFrequency : 220.0
     * batVolLow : 0
     * bypActivate : 0
     * intFault : 43
     * intVolFault : 0
     * upsType : 0
     * inTest : 0
     * gId : 2
     * diId : 5
     * bypFrequency : 220.0
     * batCapacity : 70.0
     * resTime : 10.0
     */

    private int id;
    private String name;
    private int address;
    private double batVol;
    private double batCur;
    private double batTemp;
    private double intFrequency;
    private int batVolLow;
    private int bypActivate;
    private int intFault;
    private int intVolFault;
    private int upsType;
    private int inTest;
    private int gId;
    private int diId;
    private double bypFrequency;
    private double batCapacity;
    private double resTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public double getBatVol() {
        return batVol;
    }

    public void setBatVol(double batVol) {
        this.batVol = batVol;
    }

    public double getBatCur() {
        return batCur;
    }

    public void setBatCur(double batCur) {
        this.batCur = batCur;
    }

    public double getBatTemp() {
        return batTemp;
    }

    public void setBatTemp(double batTemp) {
        this.batTemp = batTemp;
    }

    public double getIntFrequency() {
        return intFrequency;
    }

    public void setIntFrequency(double intFrequency) {
        this.intFrequency = intFrequency;
    }

    public int getBatVolLow() {
        return batVolLow;
    }

    public void setBatVolLow(int batVolLow) {
        this.batVolLow = batVolLow;
    }

    public int getBypActivate() {
        return bypActivate;
    }

    public void setBypActivate(int bypActivate) {
        this.bypActivate = bypActivate;
    }

    public int getIntFault() {
        return intFault;
    }

    public void setIntFault(int intFault) {
        this.intFault = intFault;
    }

    public int getIntVolFault() {
        return intVolFault;
    }

    public void setIntVolFault(int intVolFault) {
        this.intVolFault = intVolFault;
    }

    public int getUpsType() {
        return upsType;
    }

    public void setUpsType(int upsType) {
        this.upsType = upsType;
    }

    public int getInTest() {
        return inTest;
    }

    public void setInTest(int inTest) {
        this.inTest = inTest;
    }

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public double getBypFrequency() {
        return bypFrequency;
    }

    public void setBypFrequency(double bypFrequency) {
        this.bypFrequency = bypFrequency;
    }

    public double getBatCapacity() {
        return batCapacity;
    }

    public void setBatCapacity(double batCapacity) {
        this.batCapacity = batCapacity;
    }

    public double getResTime() {
        return resTime;
    }

    public void setResTime(double resTime) {
        this.resTime = resTime;
    }


    /**
     * bypCvol : 220
     * resTime : 0
     * intFault : 0
     * upsType : 0
     * bypAvol : 220
     * bypFrequency : 0
     * id : 2
     * batCapacity : 0
     * intCvol : 220
     * batCur : 0
     * name : 1号UPS
     * intAvol : 220
     * intVolFault : 1
     * bypBvol : 220
     * outBvol : 220
     * intBvol : 220
     * outCvol : 220
     * batTemp : 0
     * inTest : 0
     * intFrequency : 0
     * batVol : 0
     * loadA : 5
     * outAvol : 220
     * loadC : 5
     * bypActivate : 0
     * loadB : 5
     * batVolLow : 0
     *//*

    private int bypCvol;
    private int resTime;
    private int intFault;
    private int upsType;
    private int bypAvol;
    private int bypFrequency;
    private int id;
    private int batCapacity;
    private int intCvol;
    private int batCur;
    private String name;
    private int intAvol;
    private int intVolFault;
    private int bypBvol;
    private int outBvol;
    private int intBvol;
    private int outCvol;
    private int batTemp;
    private int inTest;
    private int intFrequency;
    private int batVol;
    private int loadA;
    private int outAvol;
    private int loadC;
    private int bypActivate;
    private int loadB;
    private int batVolLow;

    public static UpsBean objectFromData(String str) {

        return new Gson().fromJson(str, UpsBean.class);
    }

    public static UpsBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), UpsBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<UpsBean> arrayUpsBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<UpsBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<UpsBean> arrayUpsBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<UpsBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getBypCvol() {
        return bypCvol;
    }

    public void setBypCvol(int bypCvol) {
        this.bypCvol = bypCvol;
    }

    public int getResTime() {
        return resTime;
    }

    public void setResTime(int resTime) {
        this.resTime = resTime;
    }

    public int getIntFault() {
        return intFault;
    }

    public void setIntFault(int intFault) {
        this.intFault = intFault;
    }

    public int getUpsType() {
        return upsType;
    }

    public void setUpsType(int upsType) {
        this.upsType = upsType;
    }

    public int getBypAvol() {
        return bypAvol;
    }

    public void setBypAvol(int bypAvol) {
        this.bypAvol = bypAvol;
    }

    public int getBypFrequency() {
        return bypFrequency;
    }

    public void setBypFrequency(int bypFrequency) {
        this.bypFrequency = bypFrequency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBatCapacity() {
        return batCapacity;
    }

    public void setBatCapacity(int batCapacity) {
        this.batCapacity = batCapacity;
    }

    public int getIntCvol() {
        return intCvol;
    }

    public void setIntCvol(int intCvol) {
        this.intCvol = intCvol;
    }

    public int getBatCur() {
        return batCur;
    }

    public void setBatCur(int batCur) {
        this.batCur = batCur;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIntAvol() {
        return intAvol;
    }

    public void setIntAvol(int intAvol) {
        this.intAvol = intAvol;
    }

    public int getIntVolFault() {
        return intVolFault;
    }

    public void setIntVolFault(int intVolFault) {
        this.intVolFault = intVolFault;
    }

    public int getBypBvol() {
        return bypBvol;
    }

    public void setBypBvol(int bypBvol) {
        this.bypBvol = bypBvol;
    }

    public int getOutBvol() {
        return outBvol;
    }

    public void setOutBvol(int outBvol) {
        this.outBvol = outBvol;
    }

    public int getIntBvol() {
        return intBvol;
    }

    public void setIntBvol(int intBvol) {
        this.intBvol = intBvol;
    }

    public int getOutCvol() {
        return outCvol;
    }

    public void setOutCvol(int outCvol) {
        this.outCvol = outCvol;
    }

    public int getBatTemp() {
        return batTemp;
    }

    public void setBatTemp(int batTemp) {
        this.batTemp = batTemp;
    }

    public int getInTest() {
        return inTest;
    }

    public void setInTest(int inTest) {
        this.inTest = inTest;
    }

    public int getIntFrequency() {
        return intFrequency;
    }

    public void setIntFrequency(int intFrequency) {
        this.intFrequency = intFrequency;
    }

    public int getBatVol() {
        return batVol;
    }

    public void setBatVol(int batVol) {
        this.batVol = batVol;
    }

    public int getLoadA() {
        return loadA;
    }

    public void setLoadA(int loadA) {
        this.loadA = loadA;
    }

    public int getOutAvol() {
        return outAvol;
    }

    public void setOutAvol(int outAvol) {
        this.outAvol = outAvol;
    }

    public int getLoadC() {
        return loadC;
    }

    public void setLoadC(int loadC) {
        this.loadC = loadC;
    }

    public int getBypActivate() {
        return bypActivate;
    }

    public void setBypActivate(int bypActivate) {
        this.bypActivate = bypActivate;
    }

    public int getLoadB() {
        return loadB;
    }

    public void setLoadB(int loadB) {
        this.loadB = loadB;
    }

    public int getBatVolLow() {
        return batVolLow;
    }

    public void setBatVolLow(int batVolLow) {
        this.batVolLow = batVolLow;
    }*/
}
