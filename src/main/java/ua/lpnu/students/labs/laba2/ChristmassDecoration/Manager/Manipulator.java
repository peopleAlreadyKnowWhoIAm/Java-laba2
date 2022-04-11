package ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Manager.utils.FieldDescription;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Template;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Type;
import ua.lpnu.students.labs.laba2.ChristmassDecoration.Model.shared.Usage;


@Getter
@Setter
@NoArgsConstructor
public class Manipulator {
    List<Template> allDecorations;
    List<Template> filteredDecorations;

    List<Type> types = new LinkedList<>();

    List<String> names = new LinkedList<>();
    List<String> materials = new LinkedList<>();

    List<Usage> usages = new LinkedList<>();

    int minAmount = 0;

    float priceFrom = 0.0f, priceTo = 0.0f;

    private static final String BY_TYPE = "By type";
    private static final String BY_NAME = "By name";
    private static final String BY_MATERIALS = "By material";
    private static final String BY_USAGE = "By usage";
    private static final String AMOUNT_MORE = "More than";
    private static final String PRICE_FROM = "Price from";
    private static final String PRICE_TO = "Price to";
    private static final String BY_PRICE = "By price";
    private static final String BY_AMOUNT = "By amount";
    private static final String DIVIDER = ": ";

    private static final FieldDescription[] FILTER_TYPES = {
        new FieldDescription(null, BY_TYPE + DIVIDER),
        new FieldDescription(null, BY_NAME + DIVIDER),
        new FieldDescription(null, BY_MATERIALS + DIVIDER),
        new FieldDescription(null, BY_USAGE + DIVIDER),
        new FieldDescription(null, AMOUNT_MORE + DIVIDER),
        new FieldDescription(null, PRICE_FROM + DIVIDER),
        new FieldDescription(null, PRICE_TO + DIVIDER)
    };

    @Getter
    private static final String[] AVAILABLE_SORTING = {
        BY_NAME, BY_PRICE, BY_AMOUNT
    };

//Set sorting
    public void sortByName(boolean descending){
        filteredDecorations.sort(Comparator.comparing((a) ->{
            var tmp = (Template)a;
            return tmp.getName();
        } ));
        if(descending){
            Collections.reverse(filteredDecorations);
        }
    }

    public void sortByPrice(boolean descending){
        filteredDecorations.sort(Comparator.comparing((a) ->{
            var tmp = (Template)a;
            return tmp.getPrice();
        } ));
        if(descending){
            Collections.reverse(filteredDecorations);
        }
    }

    public void sortByAmount(boolean descending){
        filteredDecorations.sort(Comparator.comparing((a) ->{
            var tmp = (Template)a;
            return tmp.getAvalaibleAmount();
        } ));
        if(descending){
            Collections.reverse(filteredDecorations);
        }
    }

    public void sort(final String sortingType, boolean descending){
        switch(sortingType){
            case BY_NAME:
                sortByName(descending);
                break;
            case BY_PRICE:
                sortByPrice(descending);
                break;
            case BY_AMOUNT:
                sortByAmount(descending);
                break;
            default:

        }
    }

//Filtering utils
    public void filter(){
        //Filter types
        if(!types.isEmpty())filterPart(a-> types.contains(a.getType()));

        //Filter names
        if(!names.isEmpty())filterPart(a -> names.contains(a.getName()));

        //Filter materials
        if(!materials.isEmpty())filterPart(a -> materials.contains(a.getMaterial()));

        //Filter usages
        if(!usages.isEmpty())filterPart(a -> usages.contains(a.getUsage()));

        //Filter minimal amount
        if(minAmount != 0)filterPart(a -> a.getAvalaibleAmount() <= this.minAmount );

        //Filter price
        if(priceTo != 0)filterPart(a -> this.priceTo <= a.getPrice());
        if(priceFrom != 0)filterPart(a -> this.priceFrom >= a.getPrice());
    }

    private void filterPart(Predicate<Template> filter){
        if(!this.filteredDecorations.isEmpty()){
            filteredDecorations.removeIf(filter);
 
        }
    }

//Constructor
    public Manipulator(List<Template> allDecorations) {
        this.filteredDecorations = allDecorations;
        this.allDecorations= allDecorations;
    }

//Filter and get
    public List<Template> getFilteredDecorations() {
        return filteredDecorations;
    }

    public List<FieldDescription> getFilters(){
        List<FieldDescription> filters = new ArrayList<>(7);

        //Types
        var tmpTypes = FILTER_TYPES[0];
        tmpTypes.setValue((this.types.isEmpty())?new LinkedList<>(Arrays.asList(Type.ELECTRIC_DECORATION)): this.types);
        filters.add(tmpTypes);

        //Names
        var tmpNames = FILTER_TYPES[1];
        tmpNames.setValue((this.names.isEmpty())? new LinkedList<>(Arrays.asList("")): this.names);
        filters.add(tmpNames);

        //Materials
        var tmpMaterials = FILTER_TYPES[2];
        tmpMaterials.setValue((this.materials.isEmpty())? new LinkedList<>(Arrays.asList("")): this.materials);
        filters.add(tmpMaterials);

        //Usages
        var tmpUsages = FILTER_TYPES[3];
        tmpUsages.setValue((this.usages.isEmpty())? new LinkedList<>(Arrays.asList(Usage.UNKNOWN)): this.usages);;
        filters.add(tmpUsages);

        //Minimal amount
        var tmpMinAmount = FILTER_TYPES[4];
        tmpMinAmount.setValue(this.minAmount);
        filters.add(tmpMinAmount);

        //Price from
        var tmpPriceFrom = FILTER_TYPES[5];
        tmpPriceFrom.setValue(this.priceFrom);
        filters.add(tmpPriceFrom);

        //Price to
        var tmpPriceTo = FILTER_TYPES[6];
        tmpPriceTo.setValue(this.priceTo);
        filters.add(tmpPriceTo);

        return filters;
    }


    @SuppressWarnings("unchecked")
    public void setFilters(final List<FieldDescription> filters){
        this.types = (List<Type>) filters.get(0).getValue();

        this.names = (List<String>) filters.get(1).getValue();

        this.materials = (List<String>) filters.get(2).getValue();

        this.usages = (List<Usage>) filters.get(3).getValue();

        this.minAmount = (Integer) filters.get(4).getValue();

        this.priceFrom = (Float) filters.get(5).getValue();

        this.priceTo = (Float) filters.get(6).getValue();
    }

    public void setAllDecorations(final List<Template> decorations){
        this.allDecorations = decorations;
        this.filteredDecorations = decorations;
    }

}
