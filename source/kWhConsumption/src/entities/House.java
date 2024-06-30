package entities;

import enums.Profile;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class House
{
    private int id;
    private Address address;
    private int residents;
    private int kWh;
    private Profile profile;

    public House(int id, Address address, int residents, int kWh)
    {
        this.id = id;
        this.address = address;
        this.residents = residents;
        this.kWh = kWh;
    }

    @Override
    public String toString()
    {
        String formatter;
        if (getId() < 10)
        {
            formatter = String.format("%s   %s", getId(), getAddress().toString());
        } else
        {
            formatter = String.format("%s  %s", getId(), getAddress().toString());
        }
        return formatter;
    }

    public String details()
    {
        String profileAux;
        if (getProfile() == Profile.LOW)
        {
            profileAux = "Baixo Consumo";
        } else if (getProfile() == Profile.MID)
        {
            profileAux = "Médio Consumo";
        } else
        {
            profileAux = "Alto Consumo";
        }
        return String.format("%nid: %d%nLogradouro: %s%nMoradores: %s%nConsumo mensal: %s kWh%nPerfil de consumo: %s", getId(), getAddress(), getResidents(), getKWh(), profileAux);
    }

}
