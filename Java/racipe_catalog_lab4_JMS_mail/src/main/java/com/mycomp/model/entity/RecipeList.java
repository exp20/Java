package com.mycomp.model.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "recipesList")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecipeList {
    @XmlElement(name = "recipe")
    private List<Recipe> recipesList;

    public RecipeList(List<Recipe> recipesList) {
        this.recipesList = recipesList;
    }

    public RecipeList() {
    }

    public List<Recipe> getRecipesList() {
        return recipesList;
    }
}
