/*
 * spoonacular API
 * The spoonacular Nutrition, RecipeModel, and Food API allows you to access over 380,000 recipes, thousands of ingredients, 80,000 food products, and 100,000 menu items. Our food ontology and semantic recipe search engine makes it possible to search for recipes using natural language queries, such as \"gluten free brownies without sugar\" or \"low fat vegan cupcakes.\" You can automatically calculate the nutritional information for any recipe, analyze recipe costs, visualize ingredient lists, find recipes for what's in your fridge, find recipes based on special diets, nutritional requirements, or favorite ingredients, classify recipes into types and cuisines, convert ingredient amounts, or even compute an entire meal plan. With our powerful API, you can create many kinds of food and especially nutrition apps.  Special diets/dietary requirements currently available include: vegan, vegetarian, pescetarian, gluten free, grain free, dairy free, high protein, whole 30, low sodium, low carb, Paleo, ketogenic, FODMAP, and Primal.
 *
 * The version of the OpenAPI document: 1.0
 * Contact: david@spoonacular.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package me.denmau.foodfit.spoonacular.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * InlineObject4
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-01-31T13:52:34.035+01:00[Europe/Berlin]")
public class InlineObject4 {
  public static final String SERIALIZED_NAME_TITLE = "title";
  @SerializedName(SERIALIZED_NAME_TITLE)
  private String title;

  public static final String SERIALIZED_NAME_IMAGE = "image";
  @SerializedName(SERIALIZED_NAME_IMAGE)
  private File image;

  public static final String SERIALIZED_NAME_INGREDIENTS = "ingredients";
  @SerializedName(SERIALIZED_NAME_INGREDIENTS)
  private String ingredients;

  public static final String SERIALIZED_NAME_INSTRUCTIONS = "instructions";
  @SerializedName(SERIALIZED_NAME_INSTRUCTIONS)
  private String instructions;

  public static final String SERIALIZED_NAME_READY_IN_MINUTES = "readyInMinutes";
  @SerializedName(SERIALIZED_NAME_READY_IN_MINUTES)
  private BigDecimal readyInMinutes;

  public static final String SERIALIZED_NAME_SERVINGS = "servings";
  @SerializedName(SERIALIZED_NAME_SERVINGS)
  private BigDecimal servings;

  public static final String SERIALIZED_NAME_MASK = "mask";
  @SerializedName(SERIALIZED_NAME_MASK)
  private String mask;

  public static final String SERIALIZED_NAME_BACKGROUND_IMAGE = "backgroundImage";
  @SerializedName(SERIALIZED_NAME_BACKGROUND_IMAGE)
  private String backgroundImage;

  public static final String SERIALIZED_NAME_AUTHOR = "author";
  @SerializedName(SERIALIZED_NAME_AUTHOR)
  private String author;

  public static final String SERIALIZED_NAME_BACKGROUND_COLOR = "backgroundColor";
  @SerializedName(SERIALIZED_NAME_BACKGROUND_COLOR)
  private String backgroundColor;

  public static final String SERIALIZED_NAME_FONT_COLOR = "fontColor";
  @SerializedName(SERIALIZED_NAME_FONT_COLOR)
  private String fontColor;

  public static final String SERIALIZED_NAME_SOURCE = "source";
  @SerializedName(SERIALIZED_NAME_SOURCE)
  private String source;

  public InlineObject4 title(String title) {
    this.title = title;
    return this;
  }

   /**
   * The title of the recipe.
   * @return title
  **/
  @ApiModelProperty(example = "My recipe", required = true, value = "The title of the recipe.")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public InlineObject4 image(File image) {
    this.image = image;
    return this;
  }

   /**
   * The binary image of the recipe as jpg.
   * @return image
  **/
  @ApiModelProperty(required = true, value = "The binary image of the recipe as jpg.")
  public File getImage() {
    return image;
  }

  public void setImage(File image) {
    this.image = image;
  }

  public InlineObject4 ingredients(String ingredients) {
    this.ingredients = ingredients;
    return this;
  }

   /**
   * The ingredient list of the recipe, one ingredient per line (separate lines with \\n).
   * @return ingredients
  **/
  @ApiModelProperty(example = "2 cups of green beans", required = true, value = "The ingredient list of the recipe, one ingredient per line (separate lines with \\n).")
  public String getIngredients() {
    return ingredients;
  }

  public void setIngredients(String ingredients) {
    this.ingredients = ingredients;
  }

  public InlineObject4 instructions(String instructions) {
    this.instructions = instructions;
    return this;
  }

   /**
   * The instructions to make the recipe. One step per line (separate lines with \\n).
   * @return instructions
  **/
  @ApiModelProperty(example = "cook the beans", required = true, value = "The instructions to make the recipe. One step per line (separate lines with \\n).")
  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public InlineObject4 readyInMinutes(BigDecimal readyInMinutes) {
    this.readyInMinutes = readyInMinutes;
    return this;
  }

   /**
   * The number of minutes it takes to get the recipe on the table.
   * @return readyInMinutes
  **/
  @ApiModelProperty(example = "60", required = true, value = "The number of minutes it takes to get the recipe on the table.")
  public BigDecimal getReadyInMinutes() {
    return readyInMinutes;
  }

  public void setReadyInMinutes(BigDecimal readyInMinutes) {
    this.readyInMinutes = readyInMinutes;
  }

  public InlineObject4 servings(BigDecimal servings) {
    this.servings = servings;
    return this;
  }

   /**
   * The number of servings the recipe makes.
   * @return servings
  **/
  @ApiModelProperty(example = "2", required = true, value = "The number of servings the recipe makes.")
  public BigDecimal getServings() {
    return servings;
  }

  public void setServings(BigDecimal servings) {
    this.servings = servings;
  }

  public InlineObject4 mask(String mask) {
    this.mask = mask;
    return this;
  }

   /**
   * The mask to put over the recipe image (\&quot;ellipseMask\&quot;, \&quot;diamondMask\&quot;, \&quot;starMask\&quot;, \&quot;heartMask\&quot;, \&quot;potMask\&quot;, \&quot;fishMask\&quot;).
   * @return mask
  **/
  @ApiModelProperty(example = "ellipseMask", required = true, value = "The mask to put over the recipe image (\"ellipseMask\", \"diamondMask\", \"starMask\", \"heartMask\", \"potMask\", \"fishMask\").")
  public String getMask() {
    return mask;
  }

  public void setMask(String mask) {
    this.mask = mask;
  }

  public InlineObject4 backgroundImage(String backgroundImage) {
    this.backgroundImage = backgroundImage;
    return this;
  }

   /**
   * The background image (\&quot;none\&quot;,\&quot;background1\&quot;, or \&quot;background2\&quot;).
   * @return backgroundImage
  **/
  @ApiModelProperty(example = "background1", required = true, value = "The background image (\"none\",\"background1\", or \"background2\").")
  public String getBackgroundImage() {
    return backgroundImage;
  }

  public void setBackgroundImage(String backgroundImage) {
    this.backgroundImage = backgroundImage;
  }

  public InlineObject4 author(String author) {
    this.author = author;
    return this;
  }

   /**
   * The author of the recipe.
   * @return author
  **/
  @ApiModelProperty(example = "Anna Banana", value = "The author of the recipe.")
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public InlineObject4 backgroundColor(String backgroundColor) {
    this.backgroundColor = backgroundColor;
    return this;
  }

   /**
   * The background color for the recipe card as a hex-string.
   * @return backgroundColor
  **/
  @ApiModelProperty(example = "#ffffff", value = "The background color for the recipe card as a hex-string.")
  public String getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(String backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public InlineObject4 fontColor(String fontColor) {
    this.fontColor = fontColor;
    return this;
  }

   /**
   * The font color for the recipe card as a hex-string.
   * @return fontColor
  **/
  @ApiModelProperty(example = "#333333", value = "The font color for the recipe card as a hex-string.")
  public String getFontColor() {
    return fontColor;
  }

  public void setFontColor(String fontColor) {
    this.fontColor = fontColor;
  }

  public InlineObject4 source(String source) {
    this.source = source;
    return this;
  }

   /**
   * The source of the recipe.
   * @return source
  **/
  @ApiModelProperty(example = "spoonacular.com", value = "The source of the recipe.")
  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineObject4 inlineObject4 = (InlineObject4) o;
    return Objects.equals(this.title, inlineObject4.title) &&
        Objects.equals(this.image, inlineObject4.image) &&
        Objects.equals(this.ingredients, inlineObject4.ingredients) &&
        Objects.equals(this.instructions, inlineObject4.instructions) &&
        Objects.equals(this.readyInMinutes, inlineObject4.readyInMinutes) &&
        Objects.equals(this.servings, inlineObject4.servings) &&
        Objects.equals(this.mask, inlineObject4.mask) &&
        Objects.equals(this.backgroundImage, inlineObject4.backgroundImage) &&
        Objects.equals(this.author, inlineObject4.author) &&
        Objects.equals(this.backgroundColor, inlineObject4.backgroundColor) &&
        Objects.equals(this.fontColor, inlineObject4.fontColor) &&
        Objects.equals(this.source, inlineObject4.source);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, image, ingredients, instructions, readyInMinutes, servings, mask, backgroundImage, author, backgroundColor, fontColor, source);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineObject4 {\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    ingredients: ").append(toIndentedString(ingredients)).append("\n");
    sb.append("    instructions: ").append(toIndentedString(instructions)).append("\n");
    sb.append("    readyInMinutes: ").append(toIndentedString(readyInMinutes)).append("\n");
    sb.append("    servings: ").append(toIndentedString(servings)).append("\n");
    sb.append("    mask: ").append(toIndentedString(mask)).append("\n");
    sb.append("    backgroundImage: ").append(toIndentedString(backgroundImage)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    backgroundColor: ").append(toIndentedString(backgroundColor)).append("\n");
    sb.append("    fontColor: ").append(toIndentedString(fontColor)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

