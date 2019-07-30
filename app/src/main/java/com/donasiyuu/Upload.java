package com.donasiyuu;

public class Upload  {
    private String mName;
    private String mGambarUrl;
    private String mUsia;
    private String mAlamat;



    public Upload()
    {
        //Construktor Kosong

    }
    public Upload(String medittextnamalengkap, String meditusia, String meditalamatlengkap, String GambarUrl)
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
        mName = medittextnamalengkap;
        mUsia = meditusia;
        mAlamat = meditalamatlengkap;
        this.mGambarUrl = GambarUrl;

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
    public String getmGambarUrl()
    {
        return mGambarUrl;
    }
    public void setmGambarUrl(String GambarUrl)
    {
        mGambarUrl = GambarUrl;
    }

}
