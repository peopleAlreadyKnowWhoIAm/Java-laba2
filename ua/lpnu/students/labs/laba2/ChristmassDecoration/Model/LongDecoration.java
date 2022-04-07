package ua.lpnu.students.labs.laba2.ChristmassDecoration.Model;

import java.util.List;

import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.TextMenu.TextMenu;

public class LongDecoration extends Template {
    protected List<String> color;
    protected String style;
    protected int minimalLength;
    protected int widthInCentimeters;
    protected float pricePerMeter;

    public LongDecoration(String name,Usage usage, int avalaibleAmount, List<String> color, String style,
            String material, int minimalLength, int widthInCentimeters, float pricePerMeter) {
        super(name, classType, usage, avalaibleAmount);
        this.color = color;
        this.style = style;
        this.material = material;
        this.minimalLength = minimalLength;
        this.widthInCentimeters = widthInCentimeters;
        this.pricePerMeter = pricePerMeter;
    }

    public LongDecoration() {
        super(classType);
    }

    protected LongDecoration(String type){
        super(type);
    }

    @Override
    public void editAll(){
        super.editAll();

        this.color = TextMenu.editStringList(this.color, colorsStr);

        this.style = TextMenu.editString(this.style, styleStr);

        this.minimalLength = TextMenu.editInt(this.minimalLength, minimalLengthStr);

        this.widthInCentimeters = TextMenu.editInt(this.widthInCentimeters, widthStr);

        this.pricePerMeter = TextMenu.editInt(this.pricePerMeter, priceStr)/100.0f;        
    }

    @Override
    public void setAll(){
        super.setAll();

        this.color = TextMenu.setStringList(colorsStr);

        this.style = TextMenu.setString(styleStr);

        this.minimalLength = TextMenu.setInt(minimalLengthStr);

        this.widthInCentimeters = TextMenu.setInt(widthStr);

        this.pricePerMeter = TextMenu.setInt(priceStr)/100.0f;
    }

    @Override
    public String toString(){
        return super.toString() + String.format(descriptionStr,
            this.color.toString(),
            this.style,
            String.valueOf(this.minimalLength),
            String.valueOf(this.widthInCentimeters),
            String.valueOf(this.pricePerMeter)
        );
    }


    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getMinimalLength() {
        return minimalLength;
    }

    public void setMinimalLength(int minimalLength) {
        this.minimalLength = minimalLength;
    }

    public int getWidthInCentimeters() {
        return widthInCentimeters;
    }

    public void setWidthInCentimeters(int widthInCentimeters) {
        this.widthInCentimeters = widthInCentimeters;
    }

    public float getPricePerMeter() {
        return pricePerMeter;
    }

    public void setPricePerMeter(float pricePerMeter) {
        this.pricePerMeter = pricePerMeter;
    }
    private static final String classType = "Long decoration";
    
    private static final String colorsStr = "Colors of the decoration\nSend empty to confirm\n";
    private static final String styleStr = "Style: ";
    private static final String minimalLengthStr = "Minimal length selling: ";
    private static final String widthStr = "Width: ";
    private static final String priceStr = "Price per meter multiplied by 100: ";

    private static final String descriptionStr = "Colors: %s\t"+ styleStr + "%s\t" + minimalLengthStr +"%s\t" + widthStr + "%s\tPrice per meter: %s\t";
}
