#!/usr/bin/env python

import httplib

items = {'AED', 'ANG', 'ARS', 'AUD', 'BGN', 'BHD', 'BND', 'BOB', 'BRL', 'BWP', 'CAD', 'CHF', 'CLP', 'CNY', 'COP', 'CRC', 'CZK', 'DKK', 'DOP', 'DZD', 'EEK', 'EGP', 'EUR', 'FJD', 'GBP', 'HKD', 'HNL', 'HRK', 'HUF', 'IDR', 'ILS', 'INR', 'JMD', 'JOD', 'JPY', 'KES', 'KRW', 'KWD', 'KYD', 'KZT', 'LBP', 'LKR', 'LTL', 'LVL', 'MAD', 'MDL', 'MKD', 'MUR', 'MXN', 'MYR', 'NAD', 'NGN', 'NIO', 'NOK', 'NPR', 'NZD', 'OMR', 'PEN', 'PGK', 'PHP', 'PKR', 'PLN', 'PYG', 'QAR', 'RON', 'RSD', 'RUB', 'SAR', 'SCR', 'SEK', 'SGD', 'SKK', 'SLL', 'SVC', 'THB', 'TND', 'TRY', 'TTD', 'TWD', 'TZS', 'UAH', 'UGX', 'USD', 'UYU', 'UZS', 'VND', 'YER', 'ZAR', 'ZMK'}

for item in sorted(items):
  conn = httplib.HTTPConnection('www.google.com')
  conn.request('GET', '/ig/calculator?hl=en&q=1USD%3D%3F' + item)
  res = conn.getresponse()
  txt = res.read()
  value = txt.split(',')[1].split('"')[1].split(' ')[0]
  name = ' '.join(txt.split(',')[1].split('"')[1].split(' ')[1:])
  print '{"' + item + '", "' + name + '", "' + value + '"},'

