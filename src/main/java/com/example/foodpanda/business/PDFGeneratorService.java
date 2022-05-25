package com.example.foodpanda.business;

import com.example.foodpanda.model.Administrator;
import com.example.foodpanda.model.Food;
import com.example.foodpanda.model.FoodCategory;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class PDFGeneratorService
{
    public void exportPDF(Administrator currentRestaurant, HttpServletResponse response) throws IOException
    {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        //title
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph title = new Paragraph(currentRestaurant.getRestaurantName(), fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(title);

        //category list
        List<FoodCategory> categoriesList = Arrays.asList(FoodCategory.values());

        Font fontCategory = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
        fontCategory.setSize(16);
        Font fontFood = FontFactory.getFont(FontFactory.HELVETICA);
        fontFood.setSize(12);

        for(FoodCategory category : categoriesList)
        {
            Paragraph categoryTitle = new Paragraph(category.toString(), fontCategory);
            categoryTitle.setAlignment(Paragraph.ALIGN_LEFT);

            document.add(categoryTitle);

            for (Food food : currentRestaurant.getMenu())
            {
                Paragraph foodDescription = new Paragraph(food.toString(), fontFood);
                foodDescription.setAlignment(Paragraph.ALIGN_LEFT);
                if (food.getCategory() == category)
                {
                    document.add(foodDescription);
                }
            }
        }

        document.close();
    }
}
