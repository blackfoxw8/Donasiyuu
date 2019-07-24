package com.donasiyuu;

public class Upload  {
    private String mName;
    private String mGambarUrl;
    private String mUsia;
    private String mAlamat;



    public Upload(String medittextnamalengkap, String meditusia, String meditalamatlengkap, String gambarurl)
    {
        //Construktor Kosong
        this.mName = medittextnamalengkap;
        this.mUsia = meditusia;
        this.mAlamat = meditalamatlengkap;
        this.mGambarUrl = gambarurl;

        if (medittextnamalengkap.trim().equals(""))
        {
            medittextnamalengkap = "Tidak Ada";
        }
    }
    public Upload()
    {




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
    public void setmGambarUrl(String gambarUrl)
    {
        mGambarUrl = gambarUrl;
    }

}
