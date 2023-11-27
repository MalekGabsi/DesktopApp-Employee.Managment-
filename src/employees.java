public class employees{

    
    private int id,nbconges;
    private String name;
    private String surname;
    private String dob;
    private String sex;
    private String etat;
    private String phone;
    private String address;

    private double salaire;
   

    public employees(int id,String nom,String prenom, String dob, String sexe, String num, String add) {
        super();
        this.id=id;
		this.name = nom;
		this.surname = prenom;
		this.dob = dob;
		this.sex = sexe;
        this.phone=num;
        this.address=add;
    }
    public employees(String nom, String prenom, String dob, String sexe, String num, String add) {
        super();
		this.name = nom;
		this.surname = prenom;
		this.dob = dob;
		this.sex = sexe;
        this.phone=num;
        this.address=add;
    }
    // Getters
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDob() {
        return dob;
    }

    public String getSex() {
        return sex;
    }


    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getEtat() {
        return etat;
    }

    
    public void setEtat(String etat) {
        this.etat = etat;
    }

     public int getNbconges() {
        return nbconges;
    }

    
    public void setNbconges(int nbconges) {
        this.nbconges = nbconges;
    }
    public employees(String nom, String prenom,int nbconges, String dob, String etat) {
        super();
		this.name = nom;
		this.surname = prenom;
        this.nbconges =nbconges ;
		this.dob = dob;
        this.etat=etat;
    }
    public employees(int id,String nom, String prenom,int nbconges, String dob, String etat) {
        super();
        this.id=id;
		this.name = nom;
		this.surname = prenom;
        this.nbconges =nbconges ;
		this.dob = dob;
        this.etat=etat;
    }
    public double getSalaire() {
        return salaire;
    }

    
    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }
    public employees(String nom,double salaire, String dob) {
        super();
		this.name = nom;
		
        this.salaire =salaire ;
		this.dob = dob;
        this.dob=dob;
    }
    public employees(int id,String nom,double salaire, String dob) {
        super();
        this.id=id;
		this.name = nom;
		
        this.salaire =salaire ;
		this.dob = dob;
        this.dob=dob;
    }

}
