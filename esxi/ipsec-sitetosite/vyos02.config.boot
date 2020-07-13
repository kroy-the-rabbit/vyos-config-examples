interfaces {
    ethernet eth0 {
        address 10.0.54.202/24
        duplex auto
        smp-affinity auto
        speed auto
    }
    ethernet eth1 {
        address 192.168.200.1/24
        duplex auto
        smp-affinity auto
        speed auto
    }
    loopback lo {
    }
}
protocols {
    static {
        route 0.0.0.0/0 {
            next-hop 10.0.54.1 {
            }
        }
    }
}
service {
    ssh {
        port 22
    }
}
system {
    config-management {
        commit-revisions 100
    }
    console {
        device ttyS0 {
            speed 9600
        }
    }
    host-name vyos02
    login {
        user vyos {
            authentication {
                encrypted-password $6$pES87cRZSefwLBh$WuW47aiRae0eXzMzmYXNh/g3NQySduUUSXXA0jYPyNPT9d5rX5X/P2DaRD7FUstGsVVe6XaFuZepuWvFKkeFC.
                plaintext-password "vyos"
            }
            level admin
        }
    }
    name-server 10.53.53.53
    name-server 10.53.53.54
    ntp {
    }
    syslog {
        global {
            facility all {
                level info
            }
            facility protocols {
                level debug
            }
        }
    }
    time-zone UTC
}
vpn {
    ipsec {
        esp-group office-srv-esp {
            compression disable
            lifetime 1800
            mode tunnel
            pfs enable
            proposal 1 {
                encryption aes256
                hash sha1
            }
        }
        ike-group office-srv-ike {
            close-action none
            ikev2-reauth no
            key-exchange ikev1
            lifetime 3600
            proposal 1 {
                dh-group 2
                encryption aes256
                hash sha1
            }
        }
        ipsec-interfaces {
            interface eth0
        }
        site-to-site {
            peer 10.0.54.201 {
                authentication {
                    mode pre-shared-secret
                    pre-shared-secret SuperSecretSharedKey
                }
                connection-type initiate
                ike-group office-srv-ike
                ikev2-reauth inherit
                local-address 10.0.54.202
                tunnel 0 {
                    allow-nat-networks disable
                    allow-public-networks disable
                    esp-group office-srv-esp
                    local {
                        prefix 192.168.200.0/24
                    }
                    remote {
                        prefix 172.26.32.0/24
                    }
                }
            }
        }
    }
}


/* Warning: Do not remove the following line. */
/* === vyatta-config-version: "broadcast-relay@1:cluster@1:config-management@1:conntrack-sync@1:conntrack@1:dhcp-relay@2:dhcp-server@5:dns-forwarding@1:firewall@5:ipsec@5:l2tp@1:mdns@1:nat@4:ntp@1:pptp@1:qos@1:quagga@6:snmp@1:ssh@1:system@9:vrrp@2:wanloadbalance@3:webgui@1:webproxy@1:webproxy@2:zone-policy@1" === */
/* Release version: 1.2.5 */
