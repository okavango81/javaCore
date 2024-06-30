package entities;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Address
{
    private String publicPlace;

    @Override
    public String toString(){
        return String.format("%s", publicPlace);
    }
}
