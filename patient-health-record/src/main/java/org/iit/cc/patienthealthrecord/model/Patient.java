package org.iit.cc.patienthealthrecord.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "patients")
public class Patient {
    @Id
    private String id;
    private String name;
    private LocalDateTime dateOfBirth;
    private String address;
    private int phoneNumber;
    private Boolean Diabetes;
    private String gender;
    private Boolean smoker;
    private int prescriptionNumber;
    private int labResultNumber;

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public LocalDateTime getDateOfBirth() {return dateOfBirth;}

    public void setDateOfBirth(LocalDateTime dateOfBirth) {this.dateOfBirth = dateOfBirth;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public int getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(int phoneNumber) {this.phoneNumber = phoneNumber;}

    public Boolean getDiabetes() {return Diabetes;}

    public void setDiabetes(Boolean diabetes) {Diabetes = diabetes;}

    public String getGender() {return gender;}

    public void setGender(String gender) {this.gender = gender;}

    public Boolean getSmoker() {return smoker;}

    public void setSmoker(Boolean smoker) {this.smoker = smoker;}

    public int getPrescriptionNumber() {return prescriptionNumber;}

    public void setPrescriptionNumber(int prescriptionNumber) {this.prescriptionNumber = prescriptionNumber;}

    public int getLabResultNumber() {return labResultNumber;}

    public void setLabResultNumber(int labResultNumber) {this.labResultNumber = labResultNumber;}
}