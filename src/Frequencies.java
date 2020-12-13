public class Frequencies  {
    private int frequency; ///=Fréquence de la lettre
    private float relFrequency;///=Fréquence relative de la lettre

    public Frequencies(int frequency, float relFrequency) {
        this.frequency = frequency;
        this.relFrequency = relFrequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public float getRelFrequency() {
        return relFrequency;
    }

    public void setRelFrequency(float relFrequency) {
        this.relFrequency = relFrequency;
    }

    @Override
    public String toString() {
        return frequency + ":" + relFrequency;
    }

}
