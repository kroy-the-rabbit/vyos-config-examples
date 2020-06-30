interfaces {
    ethernet eth0 {
        address 10.22.22.9/24
        hw-id 00:50:56:ae:bd:00
    }
    ethernet eth1 {
        address 10.37.42.1/24
        hw-id 00:50:56:ae:b2:05
    }
    loopback lo {
    }
    wireguard wg0 {
        address 172.19.21.1/24
        description "Accounting VPN"
        peer UserADesktop {
            allowed-ips 172.19.21.2/32
            persistent-keepalive 15
            pubkey Lam/k//j43MA/L6D/1vz6cElgmXHFAldLy9LW0zWtDQ=
        }
        port 2224
        private-key roadwarriors-accounting
    }
}
nat {
    source {
        rule 10 {
            description "NAT Outbound"
            outbound-interface eth0
            source {
                address 10.37.42.0/24
            }
            translation {
                address masquerade
            }
        }
        rule 20 {
            description "NAT Outbound"
            outbound-interface eth0
            source {
                address 172.19.21.0/24
            }
            translation {
                address masquerade
            }
        }
    }
}
protocols {
    static {
        route 0.0.0.0/0 {
            next-hop 10.22.22.1 {
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
            speed 115200
        }
    }
    host-name vyos
    login {
        user vyos {
            authentication {
                encrypted-password $6$VeA.8Fniur$nQIYlhOOUcqtS38CMBKC2XReLDtvwuMgWc4GoF7Bcqz6bE1yy/nvkVtVXK8eXcN0GvOmalBcmVo/USwrfP5P61
                plaintext-password ""
            }
        }
    }
    name-server 8.8.8.8
    ntp {
        server 0.pool.ntp.org {
        }
        server 1.pool.ntp.org {
        }
        server 2.pool.ntp.org {
        }
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
}


// Warning: Do not remove the following line.
// vyos-config-version: "broadcast-relay@1:cluster@1:config-management@1:conntrack@1:conntrack-sync@1:dhcp-relay@2:dhcp-server@5:dhcpv6-server@1:dns-forwarding@2:firewall@5:https@2:interfaces@11:ipoe-server@1:ipsec@5:l2tp@3:lldp@1:mdns@1:nat@5:ntp@1:pppoe-server@3:pptp@2:qos@1:quagga@6:salt@1:snmp@1:ssh@1:sstp@2:system@17:vrrp@2:vyos-accel-ppp@2:wanloadbalance@3:webgui@1:webproxy@2:zone-policy@1"
// Release version: 1.3-kroy-202006181828
