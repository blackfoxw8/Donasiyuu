package com.donasiyuu;

public class Upload  {
    private String mName;
    private String GambarUrl;
    private String mUsia;
    private String mAlamat;



    public Upload()
    {
        //Construktor Kosong

    }
    public Upload(String medittextnamalengkap, String meditusia, String meditalamatlengkap, String gambarUrl)
    {
        if (medittextnamalengkap.trim().equals(""))
        {
            medittextnamalengkap = "Nama Tidak ADA";
        }
        else if (meditusia.trim().equals(""))
        {
            meditusia = "Usia Tidak ADA";
        }
        else if (meditalamatlengkap.trim().equals(""))
        {
            meditalamatlengkap = "Alamat Tidak ADA";
        }
        this.mName = medittextnamalengkap;
        this.mUsia = meditusia;
        this.mAlamat = meditalamatlengkap;
        this.GambarUrl = gambarUrl;

    }
    public String getmName()
    {
        return mName;
    }
    public void setmName(String medittextnamalengkap)
    {
        mName = medittextnamalengkap;
    }
    public String getmUsia()
    {
        return mUsia;
    }
    public void setmUsia(String meditusia)
    {
        mUsia = meditusia;
    }
    public String getmAlamat()
    {
        return mAlamat;
    }
    public void setmAlamat(String meditalamatlengkap)
    {
        mAlamat = meditalamatlengkap;
    }
    public String getGambarUrl()
    {
        return GambarUrl;
    }
    public void setGambarUrl(String gambarUrl)
    {
        GambarUrl = gambarUrl;
    }

}
